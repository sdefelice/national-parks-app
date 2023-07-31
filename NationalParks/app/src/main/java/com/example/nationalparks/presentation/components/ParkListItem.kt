package com.example.nationalparks.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.nationalparks.data.model.Park
import com.example.nationalparks.data.model.ParkImage

@Composable
fun ParkListItem(
    park: Park,
    onItemClick: (Park) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClick(park) },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(8.dp)
        ) {
            if(!park.images.isNullOrEmpty()) {
                val previewImage = park.images[0]
                Image(
                    painter = rememberAsyncImagePainter(previewImage.url),
                    contentDescription = previewImage.altText,
                    modifier = Modifier
                        .size(130.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "${park.fullName}",
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCard() {
    val parkImage = ParkImage(
        url = "https://www.nps.gov/common/uploads/structured_data/3C8397D6-1DD8-B71B-0BEF4C54462A1EB3.jpg",
        altText = "Silhouette of a man with backpack standing on McAfee Knob at sunset with mountains in the distance."
    )
    val park = Park(
        fullName = "Appalachian",
        images = listOf(parkImage)
    )
    ParkListItem(park = park) {
        print("Hello")
    }
}
