package com.example.lvdk.testkotlin.reducers

import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.Decrement
import com.example.lvdk.testkotlin.actions.Increment
/**
 * Created by LvDK on 2018/2/10.
 */

val countReducer = Reducer<AppState> { state, action ->
    when (action) {
        is Increment -> state.copy(counter = state.counter.plus(action.num))
        is Decrement -> state.copy(counter = state.counter.minus(action.num))
        else -> state
    }
}
