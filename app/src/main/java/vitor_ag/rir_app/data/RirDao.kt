package vitor_ag.rir_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import vitor_ag.rir_app.data.Rir

@Dao
interface RirDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRir(rir: Rir)

    @Delete
    suspend fun deleteRir(rir: Rir)

    @Query("SELECT * FROM rir WHERE id = :id")
    suspend fun getRirById(id: Int): Rir?

    @Query("SELECT * FROM rir")
    fun getRirs(): Flow<List<Rir>>
}