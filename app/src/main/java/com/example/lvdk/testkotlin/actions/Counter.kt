package com.example.lvdk.testkotlin.actions

import com.brianegan.bansa.Action
import com.example.lvdk.testkotlin.AppState

/**
 * Created by LvDK on 2018/2/10.
 */

data class INIT(val initState: AppState = AppState()) : Action

data class INCREMENT(val num: Int) : Action
data class DECREMENT(val num: Int) : Action