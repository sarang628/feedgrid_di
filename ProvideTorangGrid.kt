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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.feed.FeedScreenByReviewId
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.type.LocalFeedCompose
import com.sarang.torang.compose.feed.type.LocalPullToRefreshLayoutType
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.feed_di.CustomFeedCompose
import com.sarang.torang.di.feed_di.customPullToRefresh
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.compose.feedgrid.type.BottomDetectingLazyVerticalGridType
import com.sarang.torang.compose.feedgrid.FeedGridUiState
import com.sarang.torang.compose.feedgrid.type.LocalBottomDetectingLazyVerticalGridType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridImageLoaderType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridPullToRefresh
import com.sarang.torang.compose.feedgrid.TorangGrid
import com.sarang.torang.compose.feedgrid.TorangGridContainer
import com.sarang.torang.compose.feedgrid.type.TorangGridImageLoaderType
import com.sarang.torang.compose.feedgrid.type.TorangGridPullToRefreshType
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
    val navController = rememberNavController()
    Box(
        modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .fillMaxWidth()
    )
    {
        NavHost(
            navController       = navController,
            startDestination    = "TorangGrid"
        ) {
            composable("TorangGrid") {
                CompositionLocalProvider(
                    LocalTorangGridPullToRefresh provides customTorangGridPullToRefresh(state),
                    LocalBottomDetectingLazyVerticalGridType provides CustomBottomDetectingLazyVerticalGridType,
                    LocalTorangGridImageLoaderType provides CustomTorangGridImageLoaderType
                ) {
                    TorangGrid(
                        modifier        = Modifier.fillMaxSize(),
                        showLog         = true,
                        onFinishRefresh = {
                            state.updateState(refreshState = RefreshIndicatorState.Default)
                        },
                        onClickItem     = {
                            navController.navigate("feed/${it}")
                        }
                    )
                }
            }

            composable("feed/{id}") {
                val id = it.arguments?.getString("id")?.toIntOrNull()
                CompositionLocalProvider(
                    LocalFeedCompose provides CustomFeedCompose,
                    LocalPullToRefreshLayoutType provides customPullToRefresh,
                    LocalExpandableTextType provides CustomExpandableTextType,
                    LocalFeedImageLoader provides { CustomFeedImageLoader().invoke(it) }
                ){
                    id?.let {
                        FeedScreenByReviewId(
                            reviewId = it,
                            onBack = navController::popBackStack
                        )
                    }
                }
            }
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