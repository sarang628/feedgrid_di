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
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.feedgrid.FeedGridItemUiState
import com.sarang.torang.compose.feedgrid.FeedGridUiState
import com.sarang.torang.compose.feedgrid.TorangGrid
import com.sarang.torang.compose.feedgrid.TorangGridContainer
import com.sarang.torang.compose.feedgrid.type.LocalBottomDetectingLazyVerticalGridType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridImageLoaderType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridPullToRefresh
import com.sryang.library.BottomDetectingGridLazyColumn
import com.sryang.library.pullrefresh.RefreshIndicatorState
import com.sryang.library.pullrefresh.rememberPullToRefreshState

@Composable
fun ProvideTorangGrid(modifier: Modifier = Modifier,
                      onReview : (Int) -> Unit = {}) {
    val state = rememberPullToRefreshState()
    CompositionLocalProvider(LocalTorangGridPullToRefresh provides customTorangGridPullToRefresh(state),
        LocalBottomDetectingLazyVerticalGridType provides CustomBottomDetectingLazyVerticalGridType,
        LocalTorangGridImageLoaderType provides CustomTorangGridImageLoaderType) {
        TorangGrid(modifier        = modifier,
                   showLog         = true,
                   onFinishRefresh = { state.updateState(refreshState = RefreshIndicatorState.Default) },
                   onClickItem     = onReview)
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
            FeedGridItemUiState(
                reviewId = 0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
                0,
                "https://cdn.pixabay.com/photo/2024/02/26/19/39/monochrome-image-8598798_1280.jpg"
            ),
            FeedGridItemUiState(
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