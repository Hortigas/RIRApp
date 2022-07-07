package vitor_ag.rir_app.di

import android.app.Application
import androidx.room.Room
import com.example.material_inspection.data.RirDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vitor_ag.rir_app.data.RirRepository
import vitor_ag.rir_app.data.RirRepositoryImpl
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
    fun provideRirRepository(db: RirDatabase): RirRepository {
        return RirRepositoryImpl(db.dao)
    }
}