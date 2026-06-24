package com.dev051.berlinclock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime

class BerlinClockViewModel(
    private val getBerlinClockUseCase: GetBerlinClockUseCase,
    private val getDigitalTimeUseCase: GetDigitalTimeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private var berlinClockJob: Job? = null
    private var digitalClockJob: Job? = null

    init {
        getBerlinClock()
    }

    fun getBerlinClock() {
        digitalClockJob?.cancel()
        berlinClockJob = viewModelScope.launch(Dispatchers.Main) {
            _state.value = State.Loading
            accountForDrift()
            while (true) {
                try {
                    val state = getBerlinClockUseCase(LocalTime.now())
                    _state.value = State.BerlinSuccess(
                        state = state,
                        callback = ::getDigitalClock,
                    )
                    accountForDrift()
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    _state.value =
                        State.Error(
                            message = e.message ?: "An unexpected error occurred",
                            callback = ::getBerlinClock,
                        )
                }
            }
        }
        berlinClockJob?.start()
    }

    fun getDigitalClock() {
        berlinClockJob?.cancel()
        digitalClockJob = viewModelScope.launch(Dispatchers.Main) {
            _state.value = State.Loading
            accountForDrift()
            while (true) {
                try {
                    // necessary gimmick to simulate a world where getting the time as a Berlin clock is mundane
                    val berlinClockState = getBerlinClockUseCase(LocalTime.now())
                    val state = getDigitalTimeUseCase(berlinClockState)
                    _state.value = State.DigitalSuccess(
                        state = state,
                        callback = ::getBerlinClock
                    )
                    accountForDrift()

                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    _state.value =
                        State.Error(
                            message = e.message ?: "An unexpected error occurred",
                            callback = ::getDigitalClock,
                        )
                }
            }
        }
        digitalClockJob?.start()
    }

    private suspend fun accountForDrift() {
        val drift = System.currentTimeMillis() % 1000
        delay(1000 - drift)
    }

    sealed class State {
        data object Loading : State()
        sealed class Success(open val callback: () -> Unit) : State()
        data class BerlinSuccess(
            val state: BerlinClockState,
            override val callback: () -> Unit,
        ) : Success(callback)

        data class DigitalSuccess(
            val state: DigitalTimeState,
            override val callback: () -> Unit,
        ) : Success(callback)

        data class Error(
            val message: String,
            val callback: () -> Unit,
        ) : State()
    }
}