package com.dev051.berlinclock.presentation

import androidx.lifecycle.ViewModel
import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime

class BerlinClockViewModel(
    private val getBerlinClockUseCase: GetBerlinClockUseCase,
    private val getDigitalTimeUseCase: GetDigitalTimeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private var lastBerlinClockState: BerlinClockState? = null

    init {
        getBerlinClock()
    }

    fun getBerlinClock(time: LocalTime = LocalTime.now()) {
        _state.value = State.Loading
        try {
            val state = getBerlinClockUseCase(time)
            lastBerlinClockState = state
            _state.value = State.BerlinSuccess(state)
        } catch (e: Exception) {
            _state.value =
                State.Error(
                    message = e.message ?: "An unexpected error occurred",
                    callback = ::getBerlinClock,
                )
        }
    }

    fun getDigitalClock() {
        _state.value = State.Loading
        try {
            val berlinClockState = lastBerlinClockState
            if (berlinClockState != null) {
                val time = getDigitalTimeUseCase(berlinClockState)
                _state.value = State.DigitalSuccess(
                    DigitalTimeState(
                        time = time,
                        displaySemiColon = time.second % 2 == 0
                    )
                )
            } else {
                _state.value = State.Error(
                    message = "No Berlin Clock available",
                    callback = ::getDigitalClock,
                )
            }
        } catch (e: Exception) {
            _state.value =
                State.Error(
                    message = e.message ?: "An unexpected error occurred",
                    callback = ::getDigitalClock,
                )
        }
    }

    sealed class State {
        data object Loading : State()
        data class BerlinSuccess(val state: BerlinClockState) : State()
        data class DigitalSuccess(val state: DigitalTimeState) : State()
        data class Error(
            val message: String,
            val callback: () -> Unit,
        ) : State()
    }
}