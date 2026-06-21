package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
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
    fun `at 1AM, only the first hour is lit RED and seconds light is lit`() {
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
    fun `at 5AM, only the first hour block is lit RED and seconds light is lit`() {
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
    fun `at 6h01 AM, the first hour block is lit RED, the first hour is lit RED, the first minute is lit RED and seconds light is lit`() {
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
    fun `at 11h23 AM, the first two hour blocks are lit RED, the first hour is lit RED, the four first minute blocks are lit RRYR, the first three minutes are lit RED and seconds light is lit`() {
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
}