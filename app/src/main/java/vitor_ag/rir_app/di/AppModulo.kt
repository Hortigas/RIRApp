package vitor_ag.rir_app.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import vitor_ag.rir_app.data.RirDatabase
import vitor_ag.rir_app.data.RirRepository
import vitor_ag.rir_app.data.RirRepositoryImpl
import vitor_ag.rir_app.data.remote.AuthApi
import vitor_ag.rir_app.data.remote.AuthRepository
import vitor_ag.rir_app.data.remote.AuthRepositoryImpl
import vitor_ag.rir_app.data.remote.SharepointApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModulo {
    @Provides
    @Singleton
    fun provideRirDatabase(app: Application): RirDatabase {
        return Room.databaseBuilder(
            app,
            RirDatabase::class.java,
            "rir_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://accounts.accesscontrol.windows.net")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(authRepository: AuthRepository): SharepointApi {
        return Retrofit.Builder()
            .baseUrl("https://andradegutierrez365.sharepoint.com")
            .client(authRepository.client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRirRepository(db: RirDatabase, api: SharepointApi): RirRepository {
        return RirRepositoryImpl(db.dao, api)
    }
}