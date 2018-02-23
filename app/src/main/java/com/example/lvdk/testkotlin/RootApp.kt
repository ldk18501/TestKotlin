package com.example.lvdk.testkotlin

import android.app.Application
import com.brianegan.bansa.BansaUtils
import com.brianegan.bansa.BaseStore
import com.example.lvdk.testkotlin.middlewares.EpicMiddleware
import com.example.lvdk.testkotlin.reducers.countReducer
import com.example.lvdk.testkotlin.reducers.titleReducer
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject

/**
 * Created by LvDK on 2018/2/12.
 */

val counterStore = BaseStore(AppState(), BansaUtils.combineReducers(countReducer, titleReducer), EpicMiddleware)

val gson: Gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.declaringClass == RealmObject::class.java
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}).create()

class RootApp : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val conf : RealmConfiguration = RealmConfiguration.Builder()
                .name("myFirstRealm.realm")
                .build()
        Realm.setDefaultConfiguration(conf)
    }
}

