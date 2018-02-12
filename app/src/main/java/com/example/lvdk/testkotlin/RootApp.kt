package com.example.lvdk.testkotlin

import android.app.Application
import android.util.Log
import com.brianegan.bansa.BansaUtils
import com.brianegan.bansa.BaseStore
import com.example.lvdk.testkotlin.actions.FETCH_TITLE
import com.example.lvdk.testkotlin.actions.INIT
import com.example.lvdk.testkotlin.middlewares.epicMiddleware
import com.example.lvdk.testkotlin.reducers.countReducer
import com.example.lvdk.testkotlin.reducers.titleReducer

/**
 * Created by LvDK on 2018/2/12.
 */


val counterStore = BaseStore(AppState(), BansaUtils.combineReducers(countReducer, titleReducer), epicMiddleware)
class RootApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("testtest", "app create")

        counterStore.dispatch(INIT())
        counterStore.dispatch(FETCH_TITLE)
    }
}