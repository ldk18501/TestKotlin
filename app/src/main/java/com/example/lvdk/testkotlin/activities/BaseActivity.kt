package com.example.lvdk.testkotlin.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

open class BaseActivity : AppCompatActivity() {

    val TAG = "TestKotlin"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "${javaClass.simpleName} created.")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "${javaClass.simpleName} started.")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "${javaClass.simpleName} resumed.")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "${javaClass.simpleName} paused.")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "${javaClass.simpleName} stopped.")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "${javaClass.simpleName} restarted.")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "${javaClass.simpleName} destroyed.")
        super.onDestroy()
    }
}
