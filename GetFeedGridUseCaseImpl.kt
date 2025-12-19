package com.sarang.torang.di.feedgrid_di

import android.util.Log
import com.sarang.torang.BuildConfig
import com.sarang.torang.compose.feedgrid.FeedGridItemUiState
import com.sarang.torang.repository.FeedRepository
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
    @Provides
    fun providesGetFeedGridUseCase(
        feedRepository: FeedRepository
    ): FindAllFeedGridUseCase {
        return object : FindAllFeedGridUseCase {
            override fun invoke(): Flow<List<FeedGridItemUiState>> {
                return feedRepository.feeds.map { feedList ->
                    feedList?.map {
                        FeedGridItemUiState(
                            reviewId = it.review.reviewId,
                            imageUrl = BuildConfig.REVIEW_IMAGE_SERVER_URL + it.images.firstOrNull()?.pictureUrl
                        )
                    } ?: listOf()
                }
            }
        }
    }

    @Provides
    fun providesLoadFeedUseCase(
        feedRepository: FeedRepository
    ): LoadFeedGridUseCase {
        return object : LoadFeedGridUseCase {
            override suspend fun invoke(lastFeedId: Int) {
                Log.d("__providesLoadFeedUseCase", "load feed by last feed id : ${lastFeedId}")
                feedRepository.findById(lastFeedId)
            }
        }
    }

    @Provides
    fun providesRefreshFeedUseCase(
        feedRepository: FeedRepository
    ): RefreshFeedGirdUseCase {
        return object : RefreshFeedGirdUseCase {
            override suspend fun invoke() {
                feedRepository.loadByPage(0)
            }
        }
    }
}