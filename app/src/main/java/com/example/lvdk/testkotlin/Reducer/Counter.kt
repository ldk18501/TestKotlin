package com.example.lvdk.testkotlin.Reducer

import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.ActionCreater.DECREMENT
import com.example.lvdk.testkotlin.ActionCreater.INCREMENT
import com.example.lvdk.testkotlin.ActionCreater.INIT
import com.example.lvdk.testkotlin.AppState

/**
 * Created by LvDK on 2018/2/10.
 */

val reducer = Reducer<AppState> { state, action ->
    when (action) {
        is INIT -> AppState()
        is INCREMENT -> state.copy(counter = state.counter.plus(1))
        is DECREMENT -> state.copy(counter = state.counter.minus(1))
        else -> state
    }
}
