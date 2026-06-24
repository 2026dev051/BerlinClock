package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState

interface GetDigitalTimeUseCase {

    operator fun invoke(state: BerlinClockState): DigitalTimeState
}