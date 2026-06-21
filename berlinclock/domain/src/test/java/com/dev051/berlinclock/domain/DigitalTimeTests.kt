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
    fun `when receiving OOOOOOOOOOOOOOOOOOOOOOOF(alse), the digital time is 00h00 01sec`() {
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
}