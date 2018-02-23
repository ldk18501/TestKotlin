package com.example.lvdk.testkotlin.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.brianegan.bansa.Subscription
import com.example.lvdk.testkotlin.R
import com.example.lvdk.testkotlin.actions.Decrement
import com.example.lvdk.testkotlin.actions.Increment
import com.example.lvdk.testkotlin.actions.SetTitle
import com.example.lvdk.testkotlin.actions.StoreToRealm
import com.example.lvdk.testkotlin.counterStore
import com.example.lvdk.testkotlin.gson
import com.example.lvdk.testkotlin.models.Github
import com.example.lvdk.testkotlin.services.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

        //从realm取数据
        val savedUser: Github? = myRealm.where(Github::class.java).findFirst()
        counterStore.dispatch(SetTitle(savedUser?.name ?: ""))

        btnAdd.setOnClickListener {
            counterStore.dispatch(Increment(getInputNumber()))
        }

        btnMinus.setOnClickListener {
            counterStore.dispatch(Decrement(getInputNumber()))
        }

        btnNext.setOnClickListener {
            startActivity(Intent(this, TestRealmActivity::class.java))
        }

        // http
        Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GithubService::class.java)
                .getGithubUser("ldk18501")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    counterStore.dispatch(StoreToRealm(it, SetTitle(it.avatarUrl ?: "")))
                }, { error ->
                    Log.e("RetrofitError", error.message)
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
        myRealm.close()
    }
}



