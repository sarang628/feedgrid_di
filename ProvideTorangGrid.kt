package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun ProvideTorangGrid(modifier  : Modifier      = Modifier,
                      listState : LazyGridState = rememberLazyGridState(),
                      onReview  : (Int) -> Unit = {}) {
    val state = rememberPullToRefreshState()
    CompositionLocalProvider(
        LocalTorangGridPullToRefresh provides customTorangGridPullToRefresh(state),
                  LocalBottomDetectingLazyVerticalGridType provides CustomBottomDetectingLazyVerticalGridType,
                  LocalTorangGridImageLoaderType provides CustomTorangGridImageLoaderType) {
        TorangGrid(modifier        = modifier,
                   listState       = listState,
                   onFinishRefresh = { state.updateState(refreshState = RefreshIndicatorState.Default) },
                   onClickItem     = onReview)
    }
}