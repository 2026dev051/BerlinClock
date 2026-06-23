package com.dev051.berlinclock.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.core.design.digitalClockFont
import java.time.LocalTime

@Composable
fun DigitalClock(
    state: DigitalTimeState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "%02d".format(state.time.hour),
            fontSize = 40.sp,
            fontFamily = digitalClockFont,
        )
        Text(
            text = " : ",
            fontSize = 40.sp,
            fontFamily = digitalClockFont,
            color = if (state.displaySemiColon) Color.Unspecified else Color.Transparent
        )
        Text(
            text = "%02d".format(state.time.minute),
            fontSize = 40.sp,
            fontFamily = digitalClockFont,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DigitalClockPreview() {
    DigitalClock(
        state = DigitalTimeState(
            time = LocalTime.now(),
            displaySemiColon = true,
        ),
    )
}