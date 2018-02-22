package com.example.lvdk.testkotlin.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lvdk.testkotlin.R

class TestRealmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_realm)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
