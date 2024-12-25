package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.ui.TorangGrid
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProvideTorangGrid() {
    val state = rememberPullToRefreshState()
    val coroutine = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .fillMaxWidth()
    )
    {
        PullToRefreshLayout(modifier = Modifier
            .fillMaxSize(),
            pullRefreshLayoutState = state,
            onRefresh = {
                coroutine.launch {
                    delay(1000)
                    state.updateState(RefreshIndicatorState.Default)
                }
            }) {
            TorangGrid(
                modifier = Modifier.fillMaxSize(),
                image = provideTorangAsyncImage()
            )
        }
    }
}