package com.dev051.berlinclock.inject

import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import com.dev051.berlinclock.presentation.BerlinClockViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val berlinClockModule = module {

    factory { GetBerlinClockUseCase() }
    factory { GetDigitalTimeUseCase() }
    viewModel { BerlinClockViewModel(get(), get()) }
}