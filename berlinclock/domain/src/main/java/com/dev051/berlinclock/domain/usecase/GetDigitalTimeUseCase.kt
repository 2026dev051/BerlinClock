package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import java.time.LocalTime

class GetDigitalTimeUseCase {

    operator fun invoke(state: BerlinClockState): LocalTime {
        return LocalTime.MIDNIGHT
    }
}