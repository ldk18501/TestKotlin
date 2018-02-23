package com.example.lvdk.testkotlin.epics


/**
 * Created by LvDK on 2018/2/10.
 */

//val apiEpic = { action: Observable<Action>, store: Store<AppState> ->
//    action.ofActionType(FETCHTITLE::class.java.simpleName)
//            .flatMap({ action ->
//                Retrofit.Builder()
//                        .baseUrl((action as FETCHTITLE).url)
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                        .build()
//                        .create(GithubService::class.java)
//                        .getGithubUser(action.param)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//            }).map({ user -> StoreToRealm(user, SetTitle(user.avatarUrl?:"")) })
//}


//            .map({ action ->
//                if (action is FETCHTITLE)
//                    SetTitle(action.param)
//                else SetTitle("")
//            })