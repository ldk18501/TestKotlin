package com.example.lvdk.testkotlin.reducers

import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.SHOW_TITLE

/**
 * Created by LvDK on 2018/2/11.
 */
val titleReducer = Reducer<AppState> { state, action ->
    when (action) {
        is SHOW_TITLE -> state.copy(title = action.title)
        else -> state
    }
}
