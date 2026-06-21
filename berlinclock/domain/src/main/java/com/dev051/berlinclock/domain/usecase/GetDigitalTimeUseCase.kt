package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetDigitalTimeUseCase {

    operator fun invoke(state: BerlinClockState): LocalTime {
        val hourBlocks = state.hourBlocks.filter { it != LightState.OFF }.size
        val hours = state.hours.filter { it != LightState.OFF }.size

        val minuteBlocks = state.minuteBlocks.filter { it != LightState.OFF }.size
        val minutes = state.minutes.filter { it != LightState.OFF }.size

        // seconds are lost with the Berlin clock system.
        // As digital time also pulses every even second, we can fake it with even or odd value second
        // so the display will shift between (HH : MM) and (HH  MM)
        val seconds = if (state.isSecondEven) 0 else 1
        return LocalTime.MIDNIGHT
            .withHour((hourBlocks * 5) + hours)
            .withMinute((minuteBlocks * 5) + minuteBlocks)
            .withSecond(seconds)
    }
}