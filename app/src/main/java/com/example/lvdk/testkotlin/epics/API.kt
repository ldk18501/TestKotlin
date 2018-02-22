package com.example.lvdk.testkotlin.epics

import com.brianegan.bansa.Action
import com.brianegan.bansa.Store
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.FETCHTITLE
import com.example.lvdk.testkotlin.actions.SHOWTITLE
import com.example.lvdk.testkotlin.middlewares.ofActionType
import com.example.lvdk.testkotlin.services.GithubService
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.RealmObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by LvDK on 2018/2/10.
 */

val apiEpic = { action: Observable<Action>, store: Store<AppState> ->
    action.ofActionType(FETCHTITLE::class.java.simpleName)
            .flatMap({ action ->
                Retrofit.Builder()
                        .baseUrl((action as FETCHTITLE).url)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                        .create(GithubService::class.java)
                        .getGithubUser(action.param)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }).map({ user -> SHOWTITLE(user.avatarUrl ?: "") })
}

val gson: Gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.declaringClass == RealmObject::class.java
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}).create()

//            .map({ action ->
//                if (action is FETCHTITLE)
//                    SHOWTITLE(action.param)
//                else SHOWTITLE("")
//            })