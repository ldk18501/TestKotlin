package com.example.lvdk.testkotlin.epics

import com.brianegan.bansa.Action
import com.brianegan.bansa.Store
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.FETCH_TITLE
import com.example.lvdk.testkotlin.actions.SHOW_TITLE
import com.example.lvdk.testkotlin.middlewares.ofActionType
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * Created by LvDK on 2018/2/10.
 */
val apiEpic = { action: Observable<Action>, store: Store<AppState> ->
    action.ofActionType(FETCH_TITLE)
            .map({ _ ->
                SHOW_TITLE("hehe")
            })
}


//val apiEpic = Middleware<AppState> { store, action, next ->
//    when (action) {
//        is FETCH_TITLE -> {
//            getTitle().subscribe({ next(SHOW_TITLE(it.body()?.string()?:"")) }, { println("fetch title error: ${it.message}") })
//        }
//        else -> next(action)
//    }
//}

fun getTitle(): Observable<Response> {
    var request = Request.Builder()
            .get()
            .url("http://10.0.2.2:8888/hello")
            .build()

    val client = OkHttpClient()
    var obRes = Observable.create<Response> {
        val res = client.newCall(request).execute()
        if (!res.isSuccessful)
            it.onError(IOException("${res.code()}: ${res.message()}"))
        it.onNext(res)
        it.onComplete()
    }

    return obRes.subscribeOn(Schedulers.io())
}

//val toStringBody = Function<Response, String> {
//    it.body()?.string()?:""
//}