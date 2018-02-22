package com.example.lvdk.testkotlin

import android.app.Application
import android.util.Log
import com.brianegan.bansa.BansaUtils
import com.brianegan.bansa.BaseStore
import com.example.lvdk.testkotlin.middlewares.EpicMiddleware
import com.example.lvdk.testkotlin.reducers.countReducer
import com.example.lvdk.testkotlin.reducers.titleReducer
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlin.properties.Delegates

/**
 * Created by LvDK on 2018/2/12.
 */

val counterStore = BaseStore(AppState(), BansaUtils.combineReducers(countReducer, titleReducer), EpicMiddleware)
class RootApp : Application(){
    private var myRealm: Realm by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        Log.d("TestKotlin_appCreated", "app create")

        Realm.init(this)
        val conf : RealmConfiguration = RealmConfiguration.Builder()
                .name("myFirstRealm.realm")
                .build()
        Realm.setDefaultConfiguration(conf)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}