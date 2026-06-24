package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import com.dev051.berlinclock.domain.usecase.GetBerlinClockInteractor
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime


class BerlinClockTests {

    private val getBerlinClock = GetBerlinClockInteractor()

    @Test
    fun `at the start of the day, only the seconds light is lit (as 0 seconds is an even second)`() {
        val time = LocalTime.MIDNIGHT
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = true,
            ),
            state,
        )
    }

    @Test
    fun `at 01h00, only the hours is lit ROOO and seconds light is lit`() {
        val time = LocalTime.of(1, 0, 0)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = true,
            ),
            state,
        )
    }

    @Test
    fun `at 05h00, only the hourBlocks is lit ROOO and seconds light is lit`() {
        val time = LocalTime.of(5, 0, 0)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = true
            ),
            state,
        )
    }

    @Test
    fun `at 06h01, the hourBlocks is lit ROOO, the hours is lit ROOO, the minutes is lit ROOO and seconds light is lit`() {
        val time = LocalTime.of(6, 1, 0)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = true
            ),
            state,
        )
    }

    @Test
    fun `at 11h23, the hourBlocks is lit RROO, the hours is lit ROOO, the minuteBlocks is lit YYRYOOOOOOO, the minutes is lit RRRO and seconds light is lit`() {
        val time = LocalTime.of(11, 23, 0)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                ),
                isSecondEven = true
            ),
            state,
        )
    }

    @Test
    fun `at 17h37 23s, the hourBlocks is lit RRRO, the hours is lit RROO, the minuteBlocks is lit YYRYYRYOOOO, the minutes is lit RROO and seconds light is off`() {
        val time = LocalTime.of(17, 37, 23)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = false
            ),
            state,
        )
    }

    @Test
    fun `at 23h59 58s, everything is lit except the last hour light`() {
        val time = LocalTime.of(23, 59, 58)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                ),
                hours = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.YELLOW,
                    LightState.RED,
                    LightState.YELLOW,
                    LightState.YELLOW,
                ),
                minutes = listOf(
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                    LightState.RED,
                ),
                isSecondEven = true
            ),
            state,
        )
    }
    @Test
    fun `at 00h00 01s, everything is off`() {
        val time = LocalTime.of(0, 0, 1)
        val state = getBerlinClock(time)

        assertEquals(
            BerlinClockState(
                hourBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                hours = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minuteBlocks = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                minutes = listOf(
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                    LightState.OFF,
                ),
                isSecondEven = false
            ),
            state,
        )
    }
}