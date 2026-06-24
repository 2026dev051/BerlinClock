package com.dev051.berlinclock.domain.usecase

import com.dev051.berlinclock.domain.model.BerlinClockState
import java.time.LocalTime

interface GetBerlinClockUseCase {

    operator fun invoke(time: LocalTime): BerlinClockState
}