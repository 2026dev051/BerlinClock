package com.dev051.berlinclock.domain

import org.junit.Assert.assertEquals
import org.junit.Test


class BerlinClockTests {
    @Test
    fun `at the start of the day, only the second lamp is lit as 0 seconds is an even second`() {
        val state = BerlinClockState()

        assertEquals(state, BerlinClockState(isSecondEven = true))
    }
}