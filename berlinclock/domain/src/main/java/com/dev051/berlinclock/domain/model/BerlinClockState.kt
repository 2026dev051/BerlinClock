package com.dev051.berlinclock.domain.model

data class BerlinClockState(
    val hours: String,
    val isSecondEven: Boolean = false,
)