package vitor_ag.rir_app.features.feature_rir.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rir(
    @PrimaryKey var localId: Int?,
    var apiId: Int?,
    var title: String,
    var fornecedor: String,
    var local: String,
    var dataDeRecebimento: String,
    var categoria: String,
    var documentacaoString: String,
    var notasFiscaisString: String,
    var itensInspecionados: String,
    var observacoes: String,
    var conferenciaTecnicaString: String,
    var materiaisRecebidos: String,
    var naoConformidade: String,
    var statusDaRir: String,
    var horarioDaInspecao: String,
    var photos: List<Photo>?
)

@Entity
data class Photo(
    var uri: String,
    var createdDate: String,
    var gps: String,
    var category: String = "Inspeção do Material"
)

@Entity
data class documentacao