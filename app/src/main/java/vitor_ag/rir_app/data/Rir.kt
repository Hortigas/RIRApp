package vitor_ag.rir_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rir(
    @PrimaryKey val id: Int,
    val fornecedor: String,
    val local: String,
    val dataDeRecebimento: String,
    val categoria: String,
    val documentacaoString: String,
    val notasFiscaisString: String,
    val itensInspecionados: String,
    val observacoes: String,
    val conferenciaTecnicaString: String,
    val aprovacao1: String,
    val aprovacao2: String,
    val statusDaRir: String,
    val horarioDaInspecao: String,
    val responsavel: String,
    val photos: List<Photo>
)

@Entity
data class Photo(
    val uri: String,
    val createdDate: String,
    val gps: String,
    var category: String = "Inspeção do Material"
)