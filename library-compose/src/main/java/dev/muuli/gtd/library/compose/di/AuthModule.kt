package dev.muuli.gtd.library.compose.di



import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent // Or other appropriate component
import dev.muuli.gtd.library.compose.auth.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Provides instances at the application level
object AuthModule {

    @Provides
    @Singleton // Scope this to be a singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton // Scope AuthRepository as a singleton if appropriate
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        // FirebaseAuth is now provided by Hilt (from provideFirebaseAuth() above)
        return AuthRepository(firebaseAuth)
    }
}
