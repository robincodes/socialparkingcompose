package com.robinmaneiro.mapscomposeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.robinmaneiro.mapscomposeapp.presentation.MapEvent
import com.robinmaneiro.mapscomposeapp.presentation.MapsViewModel

@Composable
fun ReusableRoundedTextSwitch(
    viewModel: MapsViewModel?, //TODO: This is just nullable to allow the preview - investigate how to avoid this
    text: String,
    event: MapEvent,
    checked: Boolean
) {
    Box(
        modifier = Modifier
            .padding(10.dp) //Margin between box and parent
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .clickable {
                viewModel?.onEvent(event)
            }
            .padding(horizontal = 10.dp, vertical = 5.dp) //Padding within the box
    ) {

        BasicText(modifier = Modifier.align(Alignment.CenterStart), text = text)

        Switch(
            checked = checked,
            onCheckedChange = {
                viewModel?.onEvent(event)
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
        , colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF008800)
        )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewReusableRoundedTextSwitch() {
    ReusableRoundedTextSwitch(null, "Activate location", MapEvent.ToggleUberMapEnabled, true)
}