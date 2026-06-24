package com.dev051.berlinclock.inject

import com.dev051.berlinclock.domain.resourceprovider.BerlinClockResourceProvider
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import com.dev051.berlinclock.presentation.BerlinClockViewModel
import com.dev051.berlinclock.presentation.resourceprovider.AndroidBerlinClockResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val berlinClockModule = module {

    factory { GetBerlinClockUseCase() }
    factory { GetDigitalTimeUseCase() }
    single<BerlinClockResourceProvider> { AndroidBerlinClockResourceProvider(androidContext()) }
    viewModel { BerlinClockViewModel(get(), get(), get()) }
}