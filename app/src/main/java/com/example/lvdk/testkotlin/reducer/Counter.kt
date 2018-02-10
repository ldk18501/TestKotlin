package com.example.lvdk.testkotlin.reducer

import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.INCREMENT
import com.example.lvdk.testkotlin.actions.INIT
import com.example.lvdk.testkotlin.actions.SHOW_TITLE

/**
 * Created by LvDK on 2018/2/10.
 */

val reducer = Reducer<AppState> { state, action ->
    when (action) {
        is INIT -> action.initState
        is INCREMENT -> state.copy(counter = state.counter.plus(action.num))
        is DECREMENT -> state.copy(counter = state.counter.minus(action.num))
        is SHOW_TITLE -> state.copy(title = action.title)
        else -> state
    }
}
