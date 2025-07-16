package dev.muuli.gtd.library.compose.auth


import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before


@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private val repository: AuthRepository = mockk(relaxed = true)
    private lateinit var viewModel: AuthViewModel

    // Create a TestDispatcher
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

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
}
