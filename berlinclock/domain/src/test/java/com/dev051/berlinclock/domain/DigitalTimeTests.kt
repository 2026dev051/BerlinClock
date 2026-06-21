package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.LightState
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import org.junit.Test
import org.junit.Assert.assertEquals
import java.time.LocalTime

class DigitalTimeTests {

    private val getDigitalTime = GetDigitalTimeUseCase()

    @Test
    fun `when receiving OOOOOOOOOOOOOOOOOOOOOOOF(alse), the digital time is 00_00 with the colon hidden`() {
        val state = BerlinClockState(
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
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            LocalTime.of(0, 0, 1),
            digitalTime
        )
    }

    @Test
    fun `when receiving OOOOROOOOOOOOOOOOOOOOOOT(rue), the digital time is 01_00 with the colon visible`() {
        val state = BerlinClockState(
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
            isSecondEven = false
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            LocalTime.of(1, 0, 1),
            digitalTime
        )
    }

    @Test
    fun `when receiving ROOOROOOOOOOOOOOOOOOOOOT, the digital time is 06_00 with the colon visible`() {
        val state = BerlinClockState(
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
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            isSecondEven = true
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            LocalTime.of(6, 0, 0),
            digitalTime
        )
    }

    @Test
    fun `when receiving ROOOROOOROOOOOOOOOOROOOF, the digital time is 06_06 with the colon hidden`() {
        val state = BerlinClockState(
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
                LightState.YELLOW,
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
            isSecondEven = false
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            LocalTime.of(6, 6, 1),
            digitalTime
        )
    }
}