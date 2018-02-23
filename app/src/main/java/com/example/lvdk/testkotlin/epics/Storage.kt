package com.example.lvdk.testkotlin.epics

import com.brianegan.bansa.Action
import com.brianegan.bansa.Store
import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.actions.StoreToRealm
import com.example.lvdk.testkotlin.middlewares.ofActionType
import io.reactivex.Observable
import io.realm.Realm

/**
 * Created by LvDK on 2018/2/22.
 */
val storageEpic = { action: Observable<Action>, store: Store<AppState> ->
    action.ofActionType(StoreToRealm::class.java.simpleName)
            .map({ action ->
                val realmInstance = Realm.getDefaultInstance()
                realmInstance.beginTransaction()
                realmInstance.copyToRealmOrUpdate(((action as StoreToRealm).obj))
                realmInstance.commitTransaction()
                realmInstance.close()
                action.actionToDo
            })
}