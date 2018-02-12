package com.example.lvdk.testkotlin.activities

import android.os.Bundle
import android.util.Log
import ca.hardeep.kotlin.redux.Action
import com.example.lvdk.testkotlin.R
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.FETCH_TITLE
import com.example.lvdk.testkotlin.actions.INCREMENT
import com.example.lvdk.testkotlin.counterStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private fun getInputNumber(): Int {
        val inputText = if (itxtNumber.text.isNullOrBlank()) "0" else itxtNumber.text.toString()
        return if (inputText.toIntOrNull() == null) 0 else inputText.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("testtest","main create")

        setContentView(R.layout.activity_main)

        counterStore.dispatch(Action(FETCH_TITLE))

        btnAdd.setOnClickListener {
            counterStore.dispatch(Action(INCREMENT(getInputNumber())))
        }

        btnMinus.setOnClickListener {
            counterStore.dispatch(Action(DECREMENT(getInputNumber())))
        }

        println(counterStore.getState())
    }
}



