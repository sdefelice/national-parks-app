package com.example.nationalparks.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.nationalparks.data.model.ParkImage

@Composable
fun ImageGallery(images: List<ParkImage>) {
    Subheader("Image Gallery")
    Spacer(modifier = Modifier.height(15.dp))
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images) { image ->
            Box(
                contentAlignment = Alignment.Center
            ) {
                val painter = rememberAsyncImagePainter(image.url)
                if (painter.state is AsyncImagePainter.State.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
                Image(
                    painter = painter,
                    contentDescription = image.altText,
                    modifier = Modifier
                        .size(170.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}