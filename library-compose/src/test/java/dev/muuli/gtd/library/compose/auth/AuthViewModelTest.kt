package dev.muuli.gtd.library.compose.auth

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers // Import Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher // Import TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher // Or StandardTestDispatcher
import kotlinx.coroutines.test.resetMain // Import resetMain
import kotlinx.coroutines.test.setMain // Import setMain
import kotlinx.coroutines.test.runTest
import org.junit.After // Import After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private val repository: AuthRepository = mockk(relaxed = true)
    private lateinit var viewModel: AuthViewModel

    // Create a TestDispatcher
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher() // Or StandardTestDispatcher()

    @Before
    fun setup() {
        // Set the main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)
        every { repository.currentUser } returns null
        viewModel = AuthViewModel(repository)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after the test
        Dispatchers.resetMain()
    }

    @Test
    fun `onGoogleTokenReceived delegates to repository`() = runTest { // runTest uses the dispatcher from Dispatchers.Main by default if not specified
        coEvery { repository.signInWithGoogle("token") } returns mockk()
        // No need to mock repository.currentUser again here unless it changes during the test
        // every { repository.currentUser } returns mockk() // This might be redundant

        viewModel.onGoogleTokenReceived("token")

        coVerify { repository.signInWithGoogle("token") }
    }
}
