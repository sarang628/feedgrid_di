package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.feedgrid.type.TorangGridPullToRefreshType
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.PullToRefreshLayoutState

fun customTorangGridPullToRefresh(state: PullToRefreshLayoutState) : TorangGridPullToRefreshType = {
    PullToRefreshLayout(
        modifier                = Modifier.fillMaxSize(),
        pullRefreshLayoutState  = state,
        onRefresh               = it.onRefresh
    ) {
        it.content.invoke()
    }
}