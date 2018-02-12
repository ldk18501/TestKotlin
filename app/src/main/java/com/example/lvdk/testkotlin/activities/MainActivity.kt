package com.example.lvdk.testkotlin.activities

import android.os.Bundle
import com.example.lvdk.testkotlin.R
import com.example.lvdk.testkotlin.actions.DECREMENT
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
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            counterStore.dispatch(INCREMENT(getInputNumber()))
        }

        btnMinus.setOnClickListener {
            counterStore.dispatch(DECREMENT(getInputNumber()))
        }

        counterStore.subscribe({
            txtNumber.text = counterStore.state.counter.toString()
            txtTitle.text = counterStore.state.title
        })
    }

}



