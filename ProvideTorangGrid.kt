package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.ui.TorangGrid
import com.sryang.library.BottomDetectingGridLazyColumn
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState

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

        TorangGrid(
            modifier = Modifier.fillMaxSize(),
            image = provideTorangAsyncImage(),
            bottomDetectingLazyVerticalGrid = { modifier,
                                                count,
                                                columns,
                                                contentPadding,
                                                verticalArrangement,
                                                horizontalArrangement,
                                                onBottom,
                                                contents ->
                BottomDetectingGridLazyColumn(
                    modifier = modifier,
                    items = count,
                    verticalArrangement = verticalArrangement,
                    columns = columns,
                    contentPadding = contentPadding,
                    horizontalArrangement = horizontalArrangement,
                    onBottom = onBottom
                ) {
                    contents.invoke(it)
                }
            },
            pullToRefreshLayout = { contents, onRefresh ->
                PullToRefreshLayout(
                    modifier = Modifier
                        .fillMaxSize(),
                    pullRefreshLayoutState = state,
                    onRefresh = onRefresh
                ) {
                    contents.invoke()
                }
            },
            onFinishRefresh = {
                state.updateState(refreshState = RefreshIndicatorState.Default)
            }
        )
    }
}