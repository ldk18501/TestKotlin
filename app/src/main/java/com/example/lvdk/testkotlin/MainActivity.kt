package com.example.lvdk.testkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import tw.geothings.rekotlin.Action
import tw.geothings.rekotlin.StateType
import tw.geothings.rekotlin.Store
import tw.geothings.rekotlin.StoreSubscriber


data class AppState(
        val counter: Int = 0
) : StateType


data class CounterActionIncrease(val num: Int) : Action
data class CounterActionDecrease(val num: Int) : Action

private fun counterReducer(action: Action, state: AppState?): AppState {
    // if no state has been provided, create the default state
    var state = state ?: AppState()

    when (action) {
        is CounterActionIncrease -> {
            state = state.copy(counter = state.counter + action.num)
        }
        is CounterActionDecrease -> {
            state = state.copy(counter = state.counter - action.num)
        }
    }

    return state
}

val mainStore = Store(
        reducer = ::counterReducer,
        state = null
)


class MainActivity : AppCompatActivity(), StoreSubscriber<AppState> {

    private val counterLabel: TextView by lazy {
        txtNumber
    }

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

        this.buttonUp.setOnClickListener {
            val num = getInputNumber()
            mainStore.dispatch(CounterActionIncrease(num))
        }

        this.buttonDown.setOnClickListener {
            val num = getInputNumber()
            mainStore.dispatch(CounterActionDecrease(num))
        }

        mainStore.subscribe(this)
    }

    override fun newState(state: AppState) {
        // when the state changes, the UI is updated to reflect the current state
        this.counterLabel.text = "${state.counter}"
    }
}



