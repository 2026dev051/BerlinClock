package com.dev051.berlinclolck.presentation

import com.dev051.berlinclock.domain.model.BerlinClockState
import com.dev051.berlinclock.domain.model.DigitalTimeState
import com.dev051.berlinclock.domain.model.LightState
import com.dev051.berlinclock.domain.resourceprovider.BerlinClockResourceProvider
import com.dev051.berlinclock.domain.usecase.GetBerlinClockUseCase
import com.dev051.berlinclock.domain.usecase.GetDigitalTimeUseCase
import com.dev051.berlinclock.presentation.BerlinClockViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalTime

@OptIn(ExperimentalCoroutinesApi::class)
class BerlinClockViewModelTests {


    class FakeGetBerlinClockUseCase : GetBerlinClockUseCase {

        var result = BerlinClockState(
            hourBlocks = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
            hours = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
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
                LightState.OFF
            ),
            minutes = listOf(LightState.RED, LightState.OFF, LightState.OFF, LightState.OFF),
            isSecondEven = true,
        )

        override fun invoke(time: LocalTime) = result
    }

    class FakeGetDigitalTimeUseCase : GetDigitalTimeUseCase {

        var result = DigitalTimeState(
            time = LocalTime.of(15, 26),
            displayColon = true
        )

        override fun invoke(state: BerlinClockState) = result
    }

    class FakeBerlinClockResourceProvider : BerlinClockResourceProvider {
        override val genericErrorMessage = "generic error message"
        override val retryLabel = "retry"
        override val switchDisplayLabel = "switch"
    }

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when ViewModel is created, initial state is Loading`() = runTest {
        val viewModel = BerlinClockViewModel(
            getBerlinClockUseCase = FakeGetBerlinClockUseCase(),
            getDigitalTimeUseCase = FakeGetDigitalTimeUseCase(),
            resourceProvider = FakeBerlinClockResourceProvider(),
        )
        assert(viewModel.state.value is BerlinClockViewModel.State.Loading)
        viewModel.clear()
    }

    @Test
    fun `when ViewModel is created, after 1 second to account for drift, the state is BerlinSuccess`() = runTest {
            val viewModel = BerlinClockViewModel(
                getBerlinClockUseCase = FakeGetBerlinClockUseCase(),
                getDigitalTimeUseCase = FakeGetDigitalTimeUseCase(),
                resourceProvider = FakeBerlinClockResourceProvider(),
            )
            advanceTimeBy(1000L)
            assert(viewModel.state.value is BerlinClockViewModel.State.BerlinSuccess)
            viewModel.clear()
        }

    @Test
    fun `when digital time is requested, the initial state is Loading`() = runTest {
            val viewModel = BerlinClockViewModel(
                getBerlinClockUseCase = FakeGetBerlinClockUseCase(),
                getDigitalTimeUseCase = FakeGetDigitalTimeUseCase(),
                resourceProvider = FakeBerlinClockResourceProvider(),
            )
            viewModel.getDigitalClock()
            assert(viewModel.state.value is BerlinClockViewModel.State.Loading)
            viewModel.clear()
        }

    @Test
    fun `when digital time is requested, after 1 second to account for drift, the state is DigitalSuccess`() = runTest {
            val viewModel = BerlinClockViewModel(
                getBerlinClockUseCase = FakeGetBerlinClockUseCase(),
                getDigitalTimeUseCase = FakeGetDigitalTimeUseCase(),
                resourceProvider = FakeBerlinClockResourceProvider(),
            )
            viewModel.getDigitalClock()
            advanceTimeBy(1000L)
            assert(viewModel.state.value is BerlinClockViewModel.State.DigitalSuccess)
            viewModel.clear()
        }
}
