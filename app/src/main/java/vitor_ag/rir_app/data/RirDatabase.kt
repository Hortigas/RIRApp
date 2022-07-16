package vitor_ag.rir_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vitor_ag.rir_app.util.Converters

@Database(
    entities = [Rir::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class RirDatabase : RoomDatabase() {
    abstract val dao: RirDao
}