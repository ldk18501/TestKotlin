package com.example.lvdk.testkotlin.models

import io.realm.RealmObject

/**
 * Created by LvDK on 2018/2/20.
 */

open class SocialAccount(
        var name: String = "",
        var status: String = ""
) : RealmObject() {

}