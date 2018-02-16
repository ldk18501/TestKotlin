package com.example.lvdk.testkotlin.actions

import com.brianegan.bansa.Action

/**
 * Created by LvDK on 2018/2/10.
 */
data class INCREMENT(val num: Int) : Action
data class DECREMENT(val num: Int) : Action

object FETCHTITLE : Action
data class SHOWTITLE(val title: String) : Action