package com.dev051.berlinclock.inject

import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import org.koin.dsl.module

val berlinClockModule = module {

    factory { GetBerlinClockUseCase() }
    factory { GetDigitalTimeUseCase() }
}