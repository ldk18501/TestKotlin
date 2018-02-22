package com.example.lvdk.testkotlin.actions

import com.brianegan.bansa.Action

/**
 * Created by LvDK on 2018/2/10.
 */
interface ActionType

data class INCREMENT(val num: Int) : Action
data class DECREMENT(val num: Int) : Action

data class FETCHTITLE(
        val url: String,
        val param: String
) : Action

data class SHOWTITLE(val title: String) : Action