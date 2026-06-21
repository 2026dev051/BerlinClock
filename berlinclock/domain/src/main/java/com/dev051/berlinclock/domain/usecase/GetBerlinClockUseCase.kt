package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import java.time.LocalTime

class GetBerlinClockUseCase {

    operator fun invoke(time: LocalTime): BerlinClockState {
        val hours = time.hour
        val minutes = time.minute
        val seconds = time.second

        val hourBlocksLit = hours / 5
        val stringHourBlocksState = formatBlockOfFour(hourBlocksLit)

        val hoursLit = hours % 5
        val stringHoursState = formatBlockOfFour(hoursLit)

        val minutesLit = minutes % 5
        val stringMinutesState = formatBlockOfFour(minutesLit)

        return BerlinClockState(
            hourBlocks = stringHourBlocksState,
            hours = stringHoursState,
            minutes = stringMinutesState,
            isSecondEven = seconds % 2 == 0
        )
    }

    private fun formatBlockOfFour(litAmount: Int): List<LightState> {
        val blockOfFourLightState = mutableListOf<LightState>()
        for (index in 0 until 4) {
            if (index < litAmount) {
                blockOfFourLightState.add(LightState.RED)
            } else {
                blockOfFourLightState.add(LightState.OFF)
            }
        }
        return blockOfFourLightState
    }
}