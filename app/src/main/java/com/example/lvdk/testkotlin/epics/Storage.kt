package com.example.lvdk.testkotlin.epics

import com.brianegan.bansa.Action
import com.brianegan.bansa.Store
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.NOTHING
import com.example.lvdk.testkotlin.actions.STORETOREALM
import com.example.lvdk.testkotlin.middlewares.ofActionType
import io.reactivex.Observable
import io.realm.Realm

/**
 * Created by LvDK on 2018/2/22.
 */
val storageEpic = { action: Observable<Action>, store: Store<AppState> ->
    action.ofActionType(STORETOREALM::class.java.simpleName)
            .map({ action ->
                val realmInstance = Realm.getDefaultInstance()
                realmInstance.beginTransaction()
                realmInstance.copyToRealmOrUpdate((action as STORETOREALM).obj)
                realmInstance.commitTransaction()
            }).map({ _ -> NOTHING() })
}