package com.dev051.berlinclock.domain.model

import java.time.LocalTime

data class DigitalTimeState(
    val time: LocalTime,
    val displaySemiColon: Boolean,
)