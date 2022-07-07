package vitor_ag.rir_app.data

import kotlinx.coroutines.flow.Flow
import vitor_ag.rir_app.data.Rir
import vitor_ag.rir_app.data.RirDao
import vitor_ag.rir_app.data.RirRepository

class RirRepositoryImpl(
    private val dao: RirDao
) : RirRepository {
    override suspend fun insertRir(rir: Rir) {
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