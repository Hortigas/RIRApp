package vitor_ag.rir_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Rir(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val fornecedor: String,
    val local: String,
    val dataDeRecebimento: Date
)
