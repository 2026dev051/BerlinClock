package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetDigitalTimeUseCase {

    operator fun invoke(state: BerlinClockState): LocalTime {

        fun List<LightState>.getAmount(): Int = filter { it != LightState.OFF }.size

        val hourBlocks = state.hourBlocks.getAmount()
        val hours = state.hours.getAmount()

        val minuteBlocks = state.minuteBlocks.getAmount()
        val minutes = state.minutes.getAmount()

        // seconds are lost with the Berlin clock system.
        // As digital time also pulses every even second, we can fake it with even or odd value second
        // so the display will shift between (HH : MM) and (HH  MM)
        val seconds = if (state.isSecondEven) 0 else 1
        return LocalTime.of(
            (hourBlocks * 5) + hours,
            (minuteBlocks * 5) + minutes,
            seconds
        )
    }
}