package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime


class BerlinClockTests {

    private val getBerlinClock = GetBerlinClockUseCase()

    @Test
    fun `at the start of the day, only the second lamp is lit as 0 seconds is an even second`() {
        val time = LocalTime.MIDNIGHT
        val state = getBerlinClock(time)

        assertEquals(state, BerlinClockState(isSecondEven = true))
    }
}