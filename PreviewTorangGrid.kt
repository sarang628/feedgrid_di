package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.compose.feedgrid.FeedGridItemUiState
import com.sarang.torang.compose.feedgrid.FeedGridUiState
import com.sarang.torang.compose.feedgrid.TorangGridContainer
import com.sryang.library.pullrefresh.rememberPullToRefreshState

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