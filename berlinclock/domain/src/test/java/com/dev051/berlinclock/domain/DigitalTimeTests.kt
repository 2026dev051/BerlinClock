package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.berlinclock.domain.model.LightState
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import org.junit.Test
import org.junit.Assert.assertEquals
import java.time.LocalTime

class DigitalTimeTests {

    private val getDigitalTime = GetDigitalTimeUseCase()

    @Test
    fun `when receiving OOOO OOOO OOOOOOOOOOO OOOO F(alse), the digital time is 00_00 with the colon hidden`() {
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
            DigitalTimeState(LocalTime.of(0, 0, 1), false),
            digitalTime
        )
    }

    @Test
    fun `when receiving OOOO ROOO OOOOOOOOOOO OOOO T(rue), the digital time is 01_00 with the colon visible`() {
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
            DigitalTimeState(LocalTime.of(1, 0, 1), false),
            digitalTime
        )
    }

    @Test
    fun `when receiving ROOO ROOO OOOOOOOOOOO OOOO T, the digital time is 06_00 with the colon visible`() {
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
            DigitalTimeState(LocalTime.of(6, 0, 0), true),
            digitalTime
        )
    }

    @Test
    fun `when receiving ROOO ROOO ROOOOOOOOOO ROOO F, the digital time is 06_06 with the colon hidden`() {
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
            DigitalTimeState(LocalTime.of(6, 6, 1), false),
            digitalTime
        )
    }

    @Test
    fun `when receiving RRRR RRRO YYRYYRYYRYY RRRR T, the digital time is 23_59 with the colon visible`() {
        val state = BerlinClockState(
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
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            DigitalTimeState(LocalTime.of(23, 59, 0), true),
            digitalTime
        )
    }

    @Test
    fun `when receiving RROO ROOO YYRYYROOOOO RROO T, the digital time is 11_32 with the colon visible`() {
        val state = BerlinClockState(
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
                LightState.YELLOW,
                LightState.RED,
                LightState.OFF,
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
            isSecondEven = true
        )

        val digitalTime = getDigitalTime(state)

        assertEquals(
            DigitalTimeState(LocalTime.of(11, 32, 0), true),
            digitalTime
        )
    }
}