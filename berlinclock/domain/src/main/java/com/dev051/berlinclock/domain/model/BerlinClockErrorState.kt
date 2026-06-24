package com.dev051.berlinclock.domain.model

data class BerlinClockErrorState(
    val message: String,
    val action: Action?
) {
    data class Action(
        val actionText: String,
        val callback: (() -> Unit),
    )
}
