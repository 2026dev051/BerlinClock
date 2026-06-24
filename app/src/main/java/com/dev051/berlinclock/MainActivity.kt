package com.dev051.berlinclock

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.dev051.berlinclock.domain.resourceprovider.BerlinClockResourceProvider
import com.dev051.berlinclock.presentation.BerlinClock
import com.dev051.berlinclock.presentation.BerlinClockError
import com.dev051.berlinclock.presentation.BerlinClockViewModel
import com.dev051.berlinclock.presentation.DigitalClock
import com.dev051.core.design.BerlinClockTheme
import com.dev051.core.design.BnpGreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BerlinClockViewModel by viewModel()
    private val resourceProvider: BerlinClockResourceProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BerlinClockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val uiState by viewModel.state.collectAsState()
                    val configuration = LocalConfiguration.current
                    val isLandscape =
                        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            24.dp,
                            Alignment.CenterVertically
                        )
                    ) {
                        Box(modifier = if (isLandscape) Modifier.weight(1F) else Modifier) {
                            when (val state = uiState) {
                                BerlinClockViewModel.State.Loading -> {
                                    CircularProgressIndicator(
                                        color = Color.DarkGray,
                                        modifier = if (isLandscape) Modifier.align(Alignment.Center) else Modifier,
                                    )
                                }

                                is BerlinClockViewModel.State.BerlinSuccess -> {
                                    BerlinClock(state = state.state)
                                }

                                is BerlinClockViewModel.State.DigitalSuccess -> {
                                    DigitalClock(
                                        state = state.state,
                                        modifier = if (isLandscape) Modifier.align(Alignment.Center) else Modifier,
                                    )
                                }

                                is BerlinClockViewModel.State.Error -> {
                                    BerlinClockError(state = state.state)
                                }
                            }
                        }
                        (uiState as? BerlinClockViewModel.State.Success)?.let { state ->
                            Button(
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = BnpGreen,
                                ),
                                onClick = state.callback
                            ) {
                                Text(
                                    text = resourceProvider.switchDisplayLabel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
