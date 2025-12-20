package com.sarang.torang.di.feedgrid_di

import android.util.Log
import com.sarang.torang.BuildConfig
import com.sarang.torang.compose.feedgrid.FeedGridItemUiState
import com.sarang.torang.core.database.dao.FeedGridDao
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.feedgrid.FindAllFeedGridUseCase
import com.sarang.torang.usecase.feedgrid.LoadFeedGridUseCase
import com.sarang.torang.usecase.feedgrid.RefreshFeedGirdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Module
@InstallIn(SingletonComponent::class)
class GetFeedGridUseCaseImpl {
    val tag = "__GetFeedGridUseCaseImpl"
    @Provides
    fun providesGetFeedGridUseCase(
        feedFlowRepository: FeedFlowRepository
    ): FindAllFeedGridUseCase {
        return object : FindAllFeedGridUseCase {
            override fun invoke(): Flow<List<FeedGridItemUiState>> {
                return feedFlowRepository.findFeedGridFlow().map { feedList ->
                    feedList.map {
                        FeedGridItemUiState(
                            reviewId = it.reviewId,
                            imageUrl = BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl
                        )
                    }
                }
            }
        }
    }

    @Provides
    fun providesLoadFeedUseCase(
        feedLoadRepository: FeedLoadRepository
    ): LoadFeedGridUseCase {
        return object : LoadFeedGridUseCase {
            override suspend fun invoke(lastFeedId: Int) {
                Log.d("__providesLoadFeedUseCase", "load feed by last feed id : ${lastFeedId}")
                feedLoadRepository.loadFeedGird(reviewId = lastFeedId)
            }
        }
    }

    @Provides
    fun providesRefreshFeedUseCase(
        feedGridDao : FeedGridDao
    ): RefreshFeedGirdUseCase {
        return object : RefreshFeedGirdUseCase {
            override suspend fun invoke() {
                feedGridDao.deleteAll()
            }
        }
    }
}