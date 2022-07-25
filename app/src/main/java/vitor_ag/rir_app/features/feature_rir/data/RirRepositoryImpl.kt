package vitor_ag.rir_app.features.feature_rir.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody.Companion.asRequestBody
import vitor_ag.rir_app.features.feature_rir.data.remote.SharepointApi
import vitor_ag.rir_app.features.feature_rir.domain.model.Rir
import java.io.File

class RirRepositoryImpl(
    private val dao: RirDao,
    private val api: SharepointApi
) : RirRepository {
    override suspend fun insertRir(rir: Rir) {
        //dao.insertRir(rir)
        sendToSharepoint(rir)
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

    override suspend fun sendToSharepoint(rir: Rir) {
        try {
            val lastTitle = api.getRir(
                site = "DemoTiEquipe",
                list = "AppINSP - Geral",
                select = "Title",
                top = 1,
                orderBy = "ID desc"
            ).d?.results?.first()?.Title

            if (lastTitle.isNullOrBlank())
                throw Exception("title can't be null")
            //TODO empty list

            val lastTitleArr = lastTitle.split("/")
            rir.title = "${lastTitleArr.first()}/${(lastTitleArr.last().toInt() + 1)}"

            Log.d("PHOTOS", rir.photos?.last().toString())

            val respDto = api.postRir(
                site = "DemoTiEquipe",
                list = "AppINSP - Geral",
                rir = RirDtoData(
                    Id = null,
                    rir.title,
                    rir.fornecedor,
                    rir.local,
                    rir.dataDeRecebimento,
                    rir.categoria,
                    rir.documentacaoString,
                    rir.notasFiscaisString,
                    rir.itensInspecionados,
                    rir.observacoes,
                    rir.conferenciaTecnicaString,
                    rir.materiaisRecebidos,
                    rir.naoConformidade,
                    Status = rir.statusDaRir,
                    HorarioDaInspecao = rir.horarioDaInspecao,
                    DataFotos = if (!rir.photos.isNullOrEmpty()) rir.photos!!.joinToString("|") { it.createdDate } else "",
                    GPSFotos = if (!rir.photos.isNullOrEmpty()) rir.photos!!.joinToString("|") { it.gps } else ""
                )
            )

            if (respDto.d?.Id.isNullOrBlank())
                throw Error("id not found")

            rir.photos?.forEachIndexed() { index, item ->
                val file = File(item.uri)
                api.uploadAttachment(
                    site = "DemoTiEquipe",
                    list = "AppINSP - Geral",
                    id = respDto.d!!.Id!!,
                    fileName = "Foto_$index-${item.category.replace(" ", "_")}.jpg",
                    fileContent = file.asRequestBody()
                )
            }

        } catch (e: Throwable) {
            e!!.message?.let { Log.e("AG_API", it) }
        }
    }
}