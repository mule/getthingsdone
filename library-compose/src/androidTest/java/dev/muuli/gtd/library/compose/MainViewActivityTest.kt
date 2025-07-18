package dev.muuli.gtd.library.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import io.mockk.every
import io.mockk.mockk
import javax.inject.Inject
import javax.inject.Singleton
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AuthModule::class)
class MainViewActivityTest {

    @Module
    @InstallIn(SingletonComponent::class)
    object TestAuthModule {
        @Provides
        @Singleton
        fun provideAuthRepository(): FakeAuthRepository = FakeAuthRepository()
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestMainViewActivity>()

    @Inject
    lateinit var repository: FakeAuthRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun signInDisplaysMainView() {
        composeRule.onNodeWithText("Sign in with Google").assertIsDisplayed()

        val user = mockk<FirebaseUser>(relaxed = true) {
            every { email } returns "test@example.com"
        }
        repository.setUser(user)

        composeRule.runOnUiThread {
            composeRule.activity.testViewModel.onSignInResult()
        }

        composeRule.onNodeWithText("You are logged in").assertExists()
    }

    @Test
    fun signOutShowsSignInButtons() {
        val user = mockk<FirebaseUser>(relaxed = true) {
            every { email } returns "test@example.com"
        }
        repository.setUser(user)
        composeRule.runOnUiThread {
            composeRule.activity.testViewModel.onSignInResult()
        }

        composeRule.onNodeWithContentDescription("Sign Out").performClick()

        composeRule.onNodeWithText("Sign in with Google").assertIsDisplayed()
    }
}
