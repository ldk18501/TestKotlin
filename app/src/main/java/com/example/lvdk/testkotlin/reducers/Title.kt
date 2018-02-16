package com.example.lvdk.testkotlin.reducers

import android.util.Log
import com.brianegan.bansa.Reducer
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.SHOWTITLE

/**
 * Created by LvDK on 2018/2/11.
 */
val titleReducer = Reducer<AppState> { state, action ->
    when (action) {
        is SHOWTITLE -> {
            Log.e("TestKotlin_title", action.title)
            state.copy(title = action.title)
        }
        else -> state
    }
}
