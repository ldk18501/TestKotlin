package com.example.lvdk.testkotlin.middlewares

import com.brianegan.bansa.Action
import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.epics.mergedEpics
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by LvDK on 2018/2/12.
 */

val epicMiddleware = Middleware<AppState> { store, action, next ->
    val actionObservable: PublishSubject<Action> = PublishSubject.create()
    val actions = mergedEpics.map { epic->epic(actionObservable, store) }

    val combinedActions = Observable.merge(actions).publish()
    combinedActions.subscribe(actionObservable)
    combinedActions.subscribe({action -> store.dispatch(action)})
    combinedActions.connect()
    next(action)
    actionObservable.onNext(action)
}


fun Observable<Action>.ofActionType(type: Action) : Observable<Action> {
    return this.filter({ i ->
        i::class.java == type::class.java
    })
}