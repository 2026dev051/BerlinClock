package com.dev051.berlinclock.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState

@Composable
fun BerlinClock(
    state: BerlinClockState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val hours = remember { derivedStateOf { state.hours } }
        val hourBlocks = remember { derivedStateOf { state.hourBlocks } }
        val minutes = remember { derivedStateOf { state.minutes } }
        val minuteBlocks = remember { derivedStateOf { state.minuteBlocks } }
        val isSecondEven = remember { derivedStateOf { state.isSecondEven } }

        CircleLight(isSecondEven = isSecondEven)
        HorizontalLights(hourBlocks)
        HorizontalLights(hours)
        HorizontalLights(minuteBlocks)
        HorizontalLights(minutes)
    }
}

@Composable
private fun CircleLight(
    isSecondEven: State<Boolean>,
) {
    val background = if (isSecondEven.value) Color.Yellow else Color.White
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(background)
            .border(2.dp, Color.DarkGray, CircleShape)
    )
}

@Composable
private fun HorizontalLights(
    lightState: State<List<LightState>>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        lightState.value.forEach { light ->
            Box(
                modifier = Modifier
                    .weight(1F)
                    .height(48.dp)
                    .border(2.dp, Color.DarkGray)
            )
        }
    }
}

@Preview
@Composable
private fun BerlinClockPreview() {
    BerlinClock(
        state = BerlinClockState(
            hourBlocks = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
            hours = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
            minuteBlocks = listOf(
                LightState.YELLOW,
                LightState.YELLOW,
                LightState.RED,
                LightState.YELLOW,
                LightState.YELLOW,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF
            ),
            minutes = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
            isSecondEven = true,
        )
    )
}