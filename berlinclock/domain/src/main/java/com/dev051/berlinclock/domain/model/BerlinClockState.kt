package com.dev051.berlinclock.domain.model

import kotlinx.collections.immutable.ImmutableList

data class BerlinClockState(
    val hourBlocks: ImmutableList<LightState>,
    val hours: ImmutableList<LightState>,
    val minuteBlocks: ImmutableList<LightState>,
    val minutes: ImmutableList<LightState>,
    val isSecondEven: Boolean = false,
)