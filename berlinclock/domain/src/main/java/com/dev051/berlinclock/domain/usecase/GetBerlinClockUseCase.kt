package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import java.time.LocalTime

class GetBerlinClockUseCase {

    companion object {
        private const val OFF_STRING = "O"
        private const val RED_STRING = "R"
        private const val YELLOW_STRING = "Y"
    }

    operator fun invoke(time: LocalTime): BerlinClockState {
        val hours = time.hour
        val seconds = time.second

        val stringHours = formatHours(hours % 5)
        return BerlinClockState(
            hours = stringHours,
            isSecondEven = seconds % 2 == 0
        )
    }

    private fun formatHours(modulo: Int): String {
        val redHours: StringBuilder = StringBuilder()
        for(index in 0 until 4) {
            if (index < modulo) {
                redHours.append(YELLOW_STRING)
            } else {
                redHours.append(OFF_STRING)
            }
        }
        return redHours.toString()
    }
}