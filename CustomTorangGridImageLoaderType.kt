package com.sarang.torang.di.feedgrid_di

import com.sarang.torang.compose.feedgrid.type.TorangGridImageLoaderType
import com.sarang.torang.di.image.provideTorangAsyncImage

val CustomTorangGridImageLoaderType : TorangGridImageLoaderType = {
    provideTorangAsyncImage().invoke(
        it.modifier,
        it.url,
        it.iconSize,
        it.errorIconSize,
        it.contentScale
    )
}