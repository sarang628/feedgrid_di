package com.sarang.torang.di.feedgrid_di

import com.sarang.torang.BuildConfig
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.ui.GetFeedGridUseCase
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
    fun providesDeleteReviewUseCase(
        feedRepository: FeedRepository
    ): GetFeedGridUseCase {
        return object : GetFeedGridUseCase {
            override suspend fun invoke(): Flow<List<String?>> {
                return feedRepository.feeds.map { feedList ->
                    feedList.map { BuildConfig.REVIEW_IMAGE_SERVER_URL + it.images.firstOrNull()?.pictureUrl }
                }
            }
        }
    }
}