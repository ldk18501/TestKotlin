package com.example.lvdk.testkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.brianegan.bansa.Subscription
import com.example.lvdk.testkotlin.R
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.FETCHTITLE
import com.example.lvdk.testkotlin.actions.INCREMENT
import com.example.lvdk.testkotlin.actions.SHOWTITLE
import com.example.lvdk.testkotlin.counterStore
import com.example.lvdk.testkotlin.models.Github
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


class MainActivity : BaseActivity() {
    private var subscription: Subscription? = null
    private var myRealm: Realm by Delegates.notNull()

    private fun getInputNumber(): Int {
        val inputText = if (itxtNumber.text.isNullOrBlank()) "0" else itxtNumber.text.toString()
        return if (inputText.toIntOrNull() == null) 0 else inputText.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRealm = Realm.getDefaultInstance()

        subscription = counterStore.subscribe({
            Log.e("TestKotlin_state", counterStore.state.toString())
            txtNumber.text = counterStore.state.counter.toString()
            txtTitle.text = counterStore.state.title
        })

        val savedUser: Github? = myRealm.where(Github::class.java).findFirst()
        counterStore.dispatch(SHOWTITLE(savedUser?.name?:""))

        btnAdd.setOnClickListener {
            counterStore.dispatch(INCREMENT(getInputNumber()))
        }

        btnMinus.setOnClickListener {
            counterStore.dispatch(DECREMENT(getInputNumber()))
        }

        btnNext.setOnClickListener {
            startActivity(Intent(this, TestRealmActivity::class.java))
        }

        counterStore.dispatch(FETCHTITLE("https://api.github.com/", "ldk18501"))
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
        myRealm.close()
    }
}



