package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetBerlinClockUseCase {

    operator fun invoke(time: LocalTime): BerlinClockState {
        val hours = time.hour
        val seconds = time.second

        val stringHours = formatHours(hours % 5)
        return BerlinClockState(
            hours = stringHours,
            isSecondEven = seconds % 2 == 0
        )
    }

    private fun formatHours(modulo: Int): List<LightState> {
        val redHours = mutableListOf<LightState>()
        for (index in 0 until 4) {
            if (index < modulo) {
                redHours.add(LightState.RED)
            } else {
                redHours.add(LightState.OFF)
            }
        }
        return redHours
    }
}