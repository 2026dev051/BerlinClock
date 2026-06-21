package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import java.time.LocalTime

class GetBerlinClockUseCase {
    operator fun invoke(time: LocalTime): BerlinClockState {
        val seconds = time.second
        return BerlinClockState(
            isSecondEven = seconds % 2 == 0
        )
    }
}