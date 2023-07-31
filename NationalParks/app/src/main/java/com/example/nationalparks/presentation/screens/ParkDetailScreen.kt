package com.example.nationalparks.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nationalparks.presentation.components.DescriptionCard
import com.example.nationalparks.presentation.components.HeaderWithContextButton
import com.example.nationalparks.presentation.components.ImageGallery
import com.example.nationalparks.presentation.components.Tags
import com.example.nationalparks.presentation.viewmodels.NationalParksViewModel

@Composable
fun ParkDetailScreen(
    navController: NavController,
    viewModel: NationalParksViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.parks.isNotEmpty()) {
            val currentPark = state.parks[0]
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    HeaderWithContextButton(title = currentPark.fullName ?: "Error fetching name.") {
                        TextButton(onClick = { navController.navigateUp() }) {
                            Text(
                                "Back",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                if (!currentPark.images.isNullOrEmpty()) {
                    item {
                        ImageGallery(currentPark.images)
                    }
                }
                item {
                    DescriptionCard(currentPark.description)
                }
                item {
                    Tags(currentPark.activities)
                }
            }
        }
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun Preview() {

}

