package com.example.lvdk.testkotlin.actions

import com.brianegan.bansa.Action
import io.realm.RealmObject

/**
 * Created by LvDK on 2018/2/10.
 */

data class Increment(val num: Int) : Action
data class Decrement(val num: Int) : Action

data class SetTitle(val title: String) : Action

data class StoreToRealm(val obj: RealmObject, val actionToDo: Action) : Action