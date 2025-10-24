package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.ui.BottomDetectingLazyVerticalGridType
import com.sarang.torang.ui.FeedGridUiState
import com.sarang.torang.ui.LocalBottomDetectingLazyVerticalGridType
import com.sarang.torang.ui.LocalTorangGridImageLoaderType
import com.sarang.torang.ui.LocalTorangGridPullToRefresh
import com.sarang.torang.ui.TorangGrid
import com.sarang.torang.ui.TorangGridPullToRefreshType
import com.sarang.torang.ui.TorangGridContainer
import com.sarang.torang.ui.TorangGridImageLoaderType
import com.sryang.library.BottomDetectingGridLazyColumn
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.PullToRefreshLayoutState
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState


val CustomBottomDetectingLazyVerticalGridType : BottomDetectingLazyVerticalGridType = { data ->
    BottomDetectingGridLazyColumn(
    modifier                = data.modifier,
    items                   = data.items,
    verticalArrangement     = data.verticalArrangement,
    columns                 = data.columns,
    contentPadding          = data.contentPadding,
    horizontalArrangement   = data.horizontalArrangement,
    onBottom                = data.onBottom) {
        data.content.invoke(it)
    }
}

val CustomTorangGridImageLoaderType : TorangGridImageLoaderType = {
    provideTorangAsyncImage().invoke(
        it.modifier,
        it.url,
        it.iconSize,
        it.errorIconSize,
        it.contentScale
    )
}

fun customTorangGridPullToRefresh(state: PullToRefreshLayoutState) : TorangGridPullToRefreshType = {
    PullToRefreshLayout(
        modifier                = Modifier.fillMaxSize(),
        pullRefreshLayoutState  = state,
        onRefresh               = it.onRefresh
    ) {
        it.content.invoke()
    }
}


@Composable
fun ProvideTorangGrid() {
    val state = rememberPullToRefreshState()
    Box(
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .fillMaxWidth()
    )
    {
        CompositionLocalProvider(
            LocalTorangGridPullToRefresh provides customTorangGridPullToRefresh(state),
            LocalBottomDetectingLazyVerticalGridType provides CustomBottomDetectingLazyVerticalGridType,
            LocalTorangGridImageLoaderType provides CustomTorangGridImageLoaderType
        ) {
            TorangGrid(
                modifier    = Modifier.fillMaxSize(),
                onFinishRefresh = {
                    state.updateState(refreshState = RefreshIndicatorState.Default)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomDetectingLazyGrid() {
    BottomDetectingGridLazyColumn(
        modifier    = Modifier.fillMaxSize(),
        items       = 10,
        columns     = GridCells.Fixed(3)
    ) {
        Box(modifier = Modifier.size(150.dp)) {
            Text(
                text = "aaa",
                modifier = Modifier.align(Alignment.Center)
            )
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
    TorangGridContainer(
        uiState = uiState,
        modifier = Modifier.fillMaxSize(),
        onRefresh = {},
        onBottom = {}
    )
}