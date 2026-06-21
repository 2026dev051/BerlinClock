package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetBerlinClockUseCase {

    operator fun invoke(time: LocalTime): BerlinClockState {
        val hours = time.hour
        val seconds = time.second

        val hourBlocksLit = hours / 5
        val stringHourBlocksState = formatHours(hourBlocksLit)

        val hoursLit = hours % 5
        val stringHoursState = formatHours(hoursLit)
        return BerlinClockState(
            hourBlocks = stringHourBlocksState,
            hours = stringHoursState,
            isSecondEven = seconds % 2 == 0
        )
    }

    private fun formatHours(litAmount: Int): List<LightState> {
        val redHours = mutableListOf<LightState>()
        for (index in 0 until 4) {
            if (index < litAmount) {
                redHours.add(LightState.RED)
            } else {
                redHours.add(LightState.OFF)
            }
        }
        return redHours
    }
}