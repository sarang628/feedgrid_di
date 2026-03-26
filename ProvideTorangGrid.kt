package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.feedgrid.TorangGrid
import com.sarang.torang.compose.feedgrid.type.LocalBottomDetectingLazyVerticalGridType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridImageLoaderType
import com.sarang.torang.compose.feedgrid.type.LocalTorangGridPullToRefresh
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