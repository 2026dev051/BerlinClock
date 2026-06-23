package com.dev051.berlinclock.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev051.core.design.BnpGreen

@Composable
fun BerlinClockError(
    message: String,
    modifier: Modifier = Modifier,
    callback: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message
        )
        callback?.let { onClick ->
            Button(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = BnpGreen
                ),
                onClick = onClick,
            ) {
                Text("Retry")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BerlinClockErrorPreview() {
    BerlinClockError("An unexpected error occurred") {}
}