package com.example.lvdk.testkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.brianegan.bansa.BaseStore
import com.example.lvdk.testkotlin.ActionCreater.DECREMENT
import com.example.lvdk.testkotlin.ActionCreater.INCREMENT
import com.example.lvdk.testkotlin.ActionCreater.INIT
import com.example.lvdk.testkotlin.Reducer.reducer
import kotlinx.android.synthetic.main.activity_main.*


val counterStore = BaseStore(AppState(), reducer)
class MainActivity : AppCompatActivity() {

    private val buttonUp: Button by lazy {
        btnAdd
    }

    private val buttonDown: Button by lazy {
        btnMinus
    }

    private fun getInputNumber(): Int {
        val inputText = if (itxtNumber.text.isNullOrBlank()) "0" else itxtNumber.text.toString()
        return if (inputText.toIntOrNull() == null) 0 else inputText.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterStore.dispatch(INIT)

        this.buttonUp.setOnClickListener {
            counterStore.dispatch(INCREMENT)
        }

        this.buttonDown.setOnClickListener {
            counterStore.dispatch(DECREMENT)
        }

        counterStore.subscribe({
            txtNumber.text = counterStore.state.counter.toString()
        })
    }

}



