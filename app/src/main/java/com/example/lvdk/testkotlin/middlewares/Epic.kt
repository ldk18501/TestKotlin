package com.example.lvdk.testkotlin.middlewares

import android.util.Log
import com.brianegan.bansa.Action
import com.brianegan.bansa.Middleware
import com.brianegan.bansa.NextDispatcher
import com.brianegan.bansa.Store
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.epics.epics
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by LvDK on 2018/2/12.
 */

object EpicMiddleware : Middleware<AppState> {
    val inputs: PublishSubject<Action> = PublishSubject.create()

    override fun dispatch(store: Store<AppState>, action: Action, next: NextDispatcher) {
        Log.e("TestKotlin_mw", store.state.toString() + " " + action.javaClass.simpleName)

        val actions = epics.map { epic -> epic(inputs, store) }

        val combinedActions = Observable.merge(actions).publish()
        combinedActions.subscribe(inputs)
        combinedActions.subscribe({ action: Action ->
            run {
                Log.e("TestKotlin_sd", store.state.toString())
                store.dispatch(action)
            }
        })
        combinedActions.connect()

        next.dispatch(action)
        inputs.onNext(action)
    }
}

fun Observable<Action>.ofActionType(typename: String): Observable<Action> {
    return this.filter({ i ->
        //Log.e("TestKotlin_actType", i::class.java.simpleName + " vs " + type::class.java.simpleName)
        i::class.java.simpleName == typename
    })
}