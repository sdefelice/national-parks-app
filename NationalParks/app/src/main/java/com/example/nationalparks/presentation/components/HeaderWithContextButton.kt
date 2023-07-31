package com.example.nationalparks.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HeaderWithContextButton(
    title: String,
    contextButton: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.LightGray.copy(alpha = 0.5f)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            textAlign = TextAlign.Left
        )
        contextButton()
    }
}

@Preview
@Composable
fun Preview() {

    var selectedIndex by remember { mutableStateOf(-1) }

    val states = listOf("AL","AK","AZ","AR","AS","CA","CO","CT","DE","DC","FL","GA",
        "GU","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MN","MS","MO","MT")

    HeaderWithContextButton("Explore Parks in GA") {
        LargeDropdownMenu(
            label = "State",
            items = states,
            selectedIndex = selectedIndex,
            onItemSelected =  {index, _ -> selectedIndex = index},
        )
    }
}