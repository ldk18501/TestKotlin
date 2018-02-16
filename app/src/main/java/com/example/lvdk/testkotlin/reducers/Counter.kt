package com.example.lvdk.testkotlin.reducers

import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.INCREMENT
/**
 * Created by LvDK on 2018/2/10.
 */

val countReducer = Reducer<AppState> { state, action ->
    when (action) {
        is INCREMENT -> state.copy(counter = state.counter.plus(action.num))
        is DECREMENT -> state.copy(counter = state.counter.minus(action.num))
        else -> state
    }
}
