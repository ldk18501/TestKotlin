package com.example.lvdk.testkotlin.actions

import ca.hardeep.kotlin.redux.ActionType
import com.example.lvdk.testkotlin.AppState

/**
 * Created by LvDK on 2018/2/10.
 */

data class INIT(val initState: AppState = AppState()) : ActionType

data class INCREMENT(val num: Int) : ActionType
data class DECREMENT(val num: Int) : ActionType

object FETCH_TITLE : ActionType
data class SHOW_TITLE(val title: String) : ActionType