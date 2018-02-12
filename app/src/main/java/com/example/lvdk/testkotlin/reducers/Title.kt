package com.example.lvdk.testkotlin.reducers

import ca.hardeep.kotlin.redux.Action
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.SHOW_TITLE

/**
 * Created by LvDK on 2018/2/11.
 */

val titleReducer = fun(state: AppState, action: Action): AppState {
    return when (action.type::class) {
        SHOW_TITLE::class -> {
            return state.copy(title = action.payload.toString())
        }
        else -> {
            state
        }
    }
}