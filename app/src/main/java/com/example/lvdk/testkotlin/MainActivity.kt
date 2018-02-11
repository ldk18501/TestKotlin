package com.example.lvdk.testkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.brianegan.bansa.BansaUtils.combineReducers
import com.brianegan.bansa.BaseStore
import com.example.lvdk.testkotlin.actions.DECREMENT
import com.example.lvdk.testkotlin.actions.FETCH_TITLE
import com.example.lvdk.testkotlin.actions.INCREMENT
import com.example.lvdk.testkotlin.actions.INIT
import com.example.lvdk.testkotlin.epics.apiEpic
import com.example.lvdk.testkotlin.reducer.reducer
import com.example.lvdk.testkotlin.reducer.reducer2
import kotlinx.android.synthetic.main.activity_main.*


val counterStore = BaseStore(AppState(), combineReducers(reducer, reducer2), apiEpic)
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

        counterStore.dispatch(INIT())
        counterStore.dispatch(FETCH_TITLE)

        this.buttonUp.setOnClickListener {
            counterStore.dispatch(INCREMENT(getInputNumber()))
        }

        this.buttonDown.setOnClickListener {
            counterStore.dispatch(DECREMENT(getInputNumber()))
        }

        counterStore.subscribe({
            txtNumber.text = counterStore.state.counter.toString()
            txtTitle.text = counterStore.state.title
        })
    }

}



