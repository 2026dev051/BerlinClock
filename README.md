![Tests](https://github.com/2026dev051/BerlinClock/actions/workflows/launch_tests.yml/badge.svg)
# Berlin clock technical test

This project has been completed in the context of a technical test based on the following [kata](https://agilekatas.co.uk/katas/BerlinClock-Kata)

The brief required: 
* a TDD approach
* the usage of Kotlin
* Jetpack Compose for the UI
* to be completed in up to a few hours.
* the app to be easy to build.

## How to build and run

Requirements: JDK 21 and a recent Android Studio.
To run the app, open the project in Android Studio, let Gradle sync, then run the `app` configuration 
on an emulator or a physical device.

To run the tests from the command line:

```bash
./gradlew test
```

## TDD approach

I applied the Red/Green/Refactor workflow for the domain layer.
I created a test about a step into building the clock state which failed (Red)
I created or updated the code to make this test pass (Green)
I refactored the production code whenever necessary (Refactor) and moved on to the next test.

Due to time constraints, I didn't expect to have enough time to test the viewModel but I ended up having just enough 
to test in a simple no-framework approach (No Mockito, no Robolectric)
The states are tested with a temporary usage of VisibleForTesting annotation which would become 
redundant with a more robust test framework.

In the end, I added a small workflow to run the tests on push to ensure failing tests are flagged as soon as they are broken.
Even if the brief doesn't mention it, it's imo a staple for TDD projects.

## Architecture

The project uses a Clean modularization with domain and presentation.
Why no data? The context of the test would have made it overkill to me.
As a simple feature without external data source or data mapping to do thanks to the usage of LocalTime, I chose not to.
In a bigger context with time data coming from outside and requiring abstraction to be tested and injected, 
it would have made sense but not here.
The default classes for Theme / Color / Type have been moved to a core:design module so apps and 
presentation layers from any feature can implement it.

To focus on the requirements of the brief, I also didn't implement jetpack navigation.
At first, I had the idea of having a two tabs bottom bar with the clock on one and the settings on 
the other with options to choose a theme, a dark / light mode and accessibility options for color blindness 
but I finally chose not to as it was not part of the brief and could have been perceived negatively 
as a show-off attempt at review time.

## Technical choices

Such a test comes with constraints that require choices to be taken in order to be completed.
I want to mention the most obvious ones so the review process is done in full knowledge.

### StateFlow vs LiveData

This choice has not been made because I hate LiveData, I've worked with both but I believe that today, 
in a world where modern apps are built with coroutines, StateFlow has the edge.

### Koin vs no DI

I chose to use Koin to inject my dependencies for several reasons:
* Easy to setup at this scale
* Mature to be used in production project
* Goes hand in hand with Kotlin
* No codegen
* Discreet enough in the codebase to avoid distracting a reviewer from the project core.

### UseCases abstraction

Looking at the commits, you'll see it's a late decision as I didn't expect to have the time to test my viewModel.
When it became a reality, I shifted my concrete usecases to interfaces with concrete interactors.
It allowed me to use Fakes of the usecases in the ViewModel tests.

### ResourceProvider

I have a hard time with hardcoded strings and moving them to a strings file is only half the journey 
as it would make the callers have to rely on R.string
This is where ResourceProvider interfaces are handy: they declare domain side the different strings used in a module.
The presentation layer can then implement it the Android way with a context, a repository to get the 
texts remotely or any creative way to supply texts to a screen.
The other obvious gain is that the resourceProvider can be easily used in tests.

### Clock tick management

I wanted the clock to be as accurate as possible, so I tried to account for drift ticks, even if very small.
This is why I have a Loading state when first getting the clock, the drift being between 0 and 999, 
I wait for the whole second to display it.
Once the state is Success, the drift created by the code creating the Clock state each second is also removed from the delay.

### Digital Clock

The kata brief mentions the need for the Berlin clock to be switchable to a digital clock for people who can't read it.
To ensure the code reflects this hypothetical world where the berlin clock can be accessed as easily as LocalTime, 
I call the getBerlinClock usecase before turning it into a DigitalTimeState so that every tick of the 
digital clock visible in the app is created from a real BerlinClockState and not a disguised call to LocalTime.
As Berlin clock has a binary system for seconds, Berlinclock to digital only can track whether a second is even or not, 
it is enough to have a digital clock blink but not to display seconds entirely in a HH : MM : ss format.

## UI

I tried to stay as straightforward as possible with the UI as Columns and Rows mixed with a few modifiers
are more than enough to build a Berlin clock and a digital clock.
There was no need for fragments or navigation so the UI is built directly inside the Activity scaffold.
I also chose not to go too deep Style and StyleFamily side but this is something I can discuss with you 
as it would be too long to address here.

### States and uiState

ViewModel stays at the root, only states are passed to low-level composables.
The uiState is a sealed class to ensure every case is covered, no way of ending in an error-prone hybrid state.
Composables do as little as possible to avoid duplication and favoring assembly of building blocks.
The state uses ImmutableList, a stable type to ensure minimal recomposition. (hours are recomposed once per hour, 
minutes once per minute, and seconds...well you probably guessed)

### Configuration

The Berlin clock is not locked in Portrait, I wanted the screen to rotate and the Composables have been built
with that in mind. It created a challenge as I use a button in the bottom to switch the Berlin / Digital clocks
and it had to stay usable in landscape.
Due to time constraints as stated above I didn't add a Style management so the design is currently
tailored to phones and might not be optimal on tablet (but it should be functional)
