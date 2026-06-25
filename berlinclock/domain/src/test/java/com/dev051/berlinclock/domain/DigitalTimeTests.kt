package com.dev051.berlinclock.domain

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.berlinclock.domain.model.LightState
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeInteractor
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test
import org.junit.Assert.assertEquals
import java.time.LocalTime

class DigitalTimeTests {

    private val getDigitalTime = GetDigitalTimeInteractor()

    @Test
    fun `when receiving OOOO OOOO OOOOOOOOOOO OOOO F(alse), the digital time is 00_00 with the colon hidden`() {
        val state = BerlinClockState(
            hourBlocks = persistentListOf(
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            hours = persistentListOf(
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
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
            hourBlocks = persistentListOf(
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            hours = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
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
            hourBlocks = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            hours = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
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
            hourBlocks = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            hours = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
                LightState.YELLOW,
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
            hourBlocks = persistentListOf(
                LightState.RED,
                LightState.RED,
                LightState.RED,
                LightState.RED,
            ),
            hours = persistentListOf(
                LightState.RED,
                LightState.RED,
                LightState.RED,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
                LightState.YELLOW,
                LightState.YELLOW,
                LightState.YELLOW,
                LightState.YELLOW,
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
            hourBlocks = persistentListOf(
                LightState.RED,
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
            ),
            hours = persistentListOf(
                LightState.RED,
                LightState.OFF,
                LightState.OFF,
                LightState.OFF,
            ),
            minuteBlocks = persistentListOf(
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
            minutes = persistentListOf(
                LightState.YELLOW,
                LightState.YELLOW,
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