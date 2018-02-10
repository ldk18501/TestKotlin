package com.example.lvdk.testkotlin.epics

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke

import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.FETCH_TITLE

/**
 * Created by LvDK on 2018/2/10.
 */
val apiEpic = Middleware<AppState> { store, action, next ->
    when (action) {
        is FETCH_TITLE -> {

        }
        else -> next(action)
    }
}