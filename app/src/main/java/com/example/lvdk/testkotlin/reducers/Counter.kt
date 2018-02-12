package com.example.lvdk.testkotlin.reducers

import ca.hardeep.kotlin.redux.Action
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.INCREMENT

/**
 * Created by LvDK on 2018/2/10.
 */

val countReducer = fun(state: AppState, action: Action): AppState {
    return when (action.type::class) {
        INCREMENT::class -> {
            return state.copy(counter = state.counter.plus(action.payload.toString().toInt()))
        }
        DECREMENT::class -> {
            return state.copy(counter = state.counter.minus(action.payload.toString().toInt()))
        }
        else -> {
            state
        }
    }
}

