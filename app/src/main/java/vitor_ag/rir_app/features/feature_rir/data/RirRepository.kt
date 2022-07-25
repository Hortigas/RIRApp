package vitor_ag.rir_app.features.feature_rir.data

import kotlinx.coroutines.flow.Flow
import vitor_ag.rir_app.features.feature_rir.domain.model.Rir

interface RirRepository {

    suspend fun insertRir(rir: Rir)

    suspend fun deleteRir(rir: Rir)

    suspend fun getRirById(id: Int): Rir?

    fun getRirs(): Flow<List<Rir>>

    suspend fun sendToSharepoint(rir: Rir)
}