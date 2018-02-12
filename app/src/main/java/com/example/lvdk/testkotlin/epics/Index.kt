package com.example.lvdk.testkotlin.epics

import com.example.lvdk.testkotlin.AppState
import com.example.lvdk.testkotlin.middlewares.mergeEpics

/**
 * Created by LvDK on 2018/2/11.
 */
val mergedEpics = mergeEpics(apiEpic<AppState>())