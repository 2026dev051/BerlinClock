package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetDigitalTimeUseCase {

    operator fun invoke(state: BerlinClockState): LocalTime {
        val hours = state.hours.filter { it != LightState.OFF }.size
        // seconds are lost with the Berlin clock system.
        // As digital time also pulses every even second, we can fake it with even or odd value second
        // so the display will shift between (HH : MM) and (HH  MM)
        val seconds = if (state.isSecondEven) 0 else 1
        return LocalTime.MIDNIGHT
            .withHour(hours)
            .withSecond(seconds)
    }
}