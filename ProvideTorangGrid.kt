package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.ui.FeedGridUiState
import com.sarang.torang.ui.TorangGrid
import com.sarang.torang.ui._TorangGrid
import com.sryang.library.BottomDetectingGridLazyColumn
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState


@Composable
fun ProvideTorangGrid() {
    val state = rememberPullToRefreshState()
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

@Preview
@Composable
fun PreviewBottomDetectingLazyGrid() {
    BottomDetectingGridLazyColumn(
        modifier = Modifier.fillMaxSize(),
        items = 10,
        columns = GridCells.Fixed(3)
    ) {
        Box(modifier = Modifier.size(150.dp)) {
            Text("aaa", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun PreviewTorangGrid() {
    val state = rememberPullToRefreshState()
    val uiState = FeedGridUiState.Success(
        listOf(
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            Pair(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
        ), false
    )
    _TorangGrid(
        uiState = uiState,
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
        onRefresh = {},
        onBottom = {}
    )
}