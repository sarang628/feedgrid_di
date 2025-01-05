package com.sarang.torang.di.feedgrid_di

import android.util.Log
import com.sarang.torang.BuildConfig
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.ui.GetFeedGridUseCase
import com.sarang.torang.ui.LoadFeedUseCase
import com.sarang.torang.ui.RefreshFeedUseCase
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
    ): GetFeedGridUseCase {
        return object : GetFeedGridUseCase {
            override suspend fun invoke(): Flow<List<Pair<Int, String?>>> {
                return feedRepository.feeds.map { feedList ->
                    feedList.map {
                        Pair(
                            it.review.reviewId,
                            BuildConfig.REVIEW_IMAGE_SERVER_URL + it.images.firstOrNull()?.pictureUrl
                        )
                    }
                }
            }
        }
    }

    @Provides
    fun providesLoadFeedUseCase(
        feedRepository: FeedRepository
    ): LoadFeedUseCase {
        return object : LoadFeedUseCase {
            override suspend fun invoke(lastFeedId: Int) {
                Log.d("__providesLoadFeedUseCase", "load feed by last feed id : ${lastFeedId}")
                feedRepository.loadNextFeedByReivewId(lastFeedId)
            }
        }
    }

    @Provides
    fun providesRefreshFeedUseCase(
        feedRepository: FeedRepository
    ): RefreshFeedUseCase {
        return object : RefreshFeedUseCase {
            override suspend fun invoke() {
                feedRepository.loadFeedWithPage(0)
            }
        }
    }


}