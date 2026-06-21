package com.dev051.berlinclock.domain.model

data class BerlinClockState(
    val hours: List<LightState>,
    val isSecondEven: Boolean = false,
)