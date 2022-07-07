package vitor_ag.rir_app.data

import kotlinx.coroutines.flow.Flow

interface RirRepository {

    suspend fun insertRir(rir: Rir)

    suspend fun deleteRir(rir: Rir)

    suspend fun getRirById(id: Int): Rir?

    fun getRirs(): Flow<List<Rir>>
}