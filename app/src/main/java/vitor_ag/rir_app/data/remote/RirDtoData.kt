package vitor_ag.rir_app.data.remote

data class RirDtoData(
    val Id: String?,
    val Title: String,
    val Fornecedor: String,
    val Local: String,
    val DataDeRecebimento: String,
    val Categoria: String,
    val Documentacao: String,
    val NotasFiscais: String,
    val ItensInspecionados: String,
    val Observacoes: String,
    val ConferenciaTecnica: String,
    val MateriaisRecebidos: String,
    val NaoConformidade: String,
    val Status: String,
    val HorarioDaInspecao: String,
    val DataFotos: String,
    val GPSFotos: String,
)