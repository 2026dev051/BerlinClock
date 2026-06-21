package com.dev051.berlinclock.domain.model

data class BerlinClockState(
    val hourBlocks: List<LightState>,
    val hours: List<LightState>,
    val minutes: List<LightState>,
    val isSecondEven: Boolean = false,
)