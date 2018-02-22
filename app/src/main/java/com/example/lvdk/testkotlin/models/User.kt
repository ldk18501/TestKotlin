package com.example.lvdk.testkotlin.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by LvDK on 2018/2/20.
 */

open class User(
        @PrimaryKey var id: Int = 0,
        var name: String = "",
        var age : Int = 0,
        var socialAccount: SocialAccount? = null
) : RealmObject() {
    // The Kotlin compiler generates standard getters and setters.
    // Realm will overload them and code inside them is ignored.
    // So if you prefer you can also just have empty abstract methods.
}