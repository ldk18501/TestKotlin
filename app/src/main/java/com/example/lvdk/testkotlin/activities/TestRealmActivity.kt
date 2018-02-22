package com.example.lvdk.testkotlin.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.lvdk.testkotlin.R
import com.example.lvdk.testkotlin.models.SocialAccount
import com.example.lvdk.testkotlin.models.User
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_test_realm.*
import kotlin.properties.Delegates

class TestRealmActivity : AppCompatActivity() {

    private var myRealm: Realm by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_realm)

        myRealm = Realm.getDefaultInstance()

        btnAdd2Realm.setOnClickListener {
            create(myRealm)
        }

        btnDisplayAll.setOnClickListener {
            displayAll(myRealm)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myRealm.close()
    }

    private fun create(realm: Realm) {
        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.executeTransaction {
            // Add a person
            val user = realm.createObject<User>(0)
            user.name = "John"
            user.age = 14

            val sa = realm.createObject<SocialAccount>()
            sa.name = "FaceBook"
            sa.status = "Boring"
            user.socialAccount = sa
        }
    }

    private fun displayAll(realm : Realm){
        // Find the first person (no query conditions) and read a field
        val user = realm.where<User>().findFirst()!!
        Log.e("TestKotlin_realm", user.name + " " + user.age + " " + user.socialAccount?.name + " " + user.socialAccount?.status)
    }

}
