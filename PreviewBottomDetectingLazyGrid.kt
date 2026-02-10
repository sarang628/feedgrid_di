package com.sarang.torang.di.feedgrid_di

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.library.BottomDetectingGridLazyColumn

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