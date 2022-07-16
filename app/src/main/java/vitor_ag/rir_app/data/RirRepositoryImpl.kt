package vitor_ag.rir_app.data

import kotlinx.coroutines.flow.Flow
import vitor_ag.rir_app.data.remote.SharepointApi

class RirRepositoryImpl(
    private val dao: RirDao,
    private val api: SharepointApi
) : RirRepository {
    override suspend fun insertRir(rir: Rir) {
        api.getRir(site = "DemoTiEquipe", list = "AppCID - Geral")
        dao.insertRir(rir)
    }

    override suspend fun deleteRir(rir: Rir) {
        dao.deleteRir(rir)
    }

    override suspend fun getRirById(id: Int): Rir? {
        return dao.getRirById(id)
    }

    override fun getRirs(): Flow<List<Rir>> {
        return dao.getRirs()
    }
}