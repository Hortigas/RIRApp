package vitor_ag.rir_app.ui.add_edit_rir

data class NotaFiscal(
    val Nota: String,
    val Consta: Boolean = false,
    val Certificados: List<String> = listOf(),
    val Material: String = "",
    val Quantidade: String = "",
    val Codigo: String = "",
    val Certificado: String = ""
)