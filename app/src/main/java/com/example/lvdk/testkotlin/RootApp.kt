package com.example.lvdk.testkotlin

import android.app.Application
import android.util.Log
import ca.hardeep.kotlin.redux.applyMiddleware
import ca.hardeep.kotlin.redux.combineReducers
import ca.hardeep.kotlin.redux.createStoreWithEnhancer
import com.example.lvdk.testkotlin.middlewares.epicMiddleware
import com.example.lvdk.testkotlin.reducers.countReducer
import com.example.lvdk.testkotlin.reducers.titleReducer

/**
 * Created by LvDK on 2018/2/11.
 */

val combinedReducers = combineReducers<AppState>(countReducer, titleReducer);
val counterStore = createStoreWithEnhancer<AppState>(combinedReducers, AppState(), applyMiddleware<AppState>(listOf(epicMiddleware)))

class RootApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("testtest", "app create")
    }
}

