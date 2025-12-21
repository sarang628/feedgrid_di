package com.sarang.torang.di.feedgrid_di

import com.sarang.torang.compose.feedgrid.type.BottomDetectingLazyVerticalGridType
import com.sryang.library.BottomDetectingGridLazyColumn

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