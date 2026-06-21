package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime


class BerlinClockTests {

    private val getBerlinClock = GetBerlinClockUseCase()

    @Test
    fun `at the start of the day, only the seconds light is lit (as 0 seconds is an even second)`() {
        val time = LocalTime.MIDNIGHT
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hours = "OOOO",
                isSecondEven = true,
            ),
            state,
        )
    }

    @Test
    fun `at 1AM, only the first hour and seconds light is lit`() {
        val time = LocalTime.of(1, 0, 0)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hours = "YOOO",
                isSecondEven = true,
            ),
            state,
        )
    }
}