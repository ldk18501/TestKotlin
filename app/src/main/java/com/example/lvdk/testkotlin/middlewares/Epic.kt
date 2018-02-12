package com.example.lvdk.testkotlin.middlewares

import ca.hardeep.kotlin.redux.*
import com.example.lvdk.testkotlin.epics.mergedEpics
import io.reactivex.Observable
import io.reactivex.Observable.merge
import io.reactivex.subjects.PublishSubject
/**
 * Created by LvDK on 2018/2/11.
 */

val epicMiddleware = createEpicMiddleware(mergedEpics, null)


typealias Epic<S> = (action: Observable<Action>, store: Store<S>, dependencies: Any?) -> Observable<Action>
typealias MergedEpics<S> = (action: Observable<Action>, store: Store<S>, dependencies: Any?) -> Observable<Action>

fun Observable<Action>.ofActionType(type: ActionType) : Observable<Action> {
    return this.filter({ i ->
        i.type::class.java == type::class.java
    })
}

fun <S> mergeEpics(vararg epics: Epic<S>) : MergedEpics<S> {
    return { action, store, dependencies ->
        merge(
                epics.map { epic ->
                    epic(action, store, dependencies)
                }
        )
    }
}


fun <S> createEpicMiddleware(rootEpic: MergedEpics<S>, dependencies: Any?): Middleware<S> {

    val actionObservable: PublishSubject<Action> = PublishSubject.create()
    val epicObservable: PublishSubject<MergedEpics<S>> = PublishSubject.create()

    return { store ->
        val next: (Next) -> (Action) -> Action = { next ->
            epicObservable.switchMap { e ->
                e(actionObservable, store, dependencies)
            }.subscribe({ action ->
                        try {
                            store.dispatch(action)
                        } catch (e: Error) {
                            println(e)
                        }
                    })

            epicObservable.onNext(rootEpic)

            val callNext: (Action) -> Action = { action ->
                val result = next(action)
                actionObservable.onNext(action)
                result
            }
            callNext
        }
        next
    }
}