package vitor_ag.rir_app.features.feature_rir.data.remote.dto

import vitor_ag.rir_app.features.feature_rir.domain.model.Rir

data class RirListDto(
    val d: D
) {
    data class D(
        val __next: String,
        val results: List<Result>
    ) {
        data class Result(
            val AttachmentFiles: AttachmentFiles,
            val Attachments: Boolean,
            val AuthorId: Int,
            val Categoria: String,
            val ComplianceAssetId: Any,
            val ConferenciaTecnica: String,
            val ContentType: ContentType,
            val ContentTypeId: String,
            val Created: String,
            val DataDeRecebimento: String,
            val DataFotos: String,
            val Documentacao: String,
            val EditorId: Int,
            val FieldValuesAsHtml: FieldValuesAsHtml,
            val FieldValuesAsText: FieldValuesAsText,
            val FieldValuesForEdit: FieldValuesForEdit,
            val File: File,
            val FileSystemObjectType: Int,
            val FirstUniqueAncestorSecurableObject: FirstUniqueAncestorSecurableObject,
            val Folder: Folder,
            val Fornecedor: String,
            val GPSFotos: String,
            val GUID: String,
            val GetDlpPolicyTip: GetDlpPolicyTip,
            val HorarioDaInspecao: String,
            val ID: Int,
            val Id: Int,
            val ItensInspecionados: String,
            val LikedByInformation: LikedByInformation,
            val Local: String,
            val MateriaisRecebidos: String,
            val MateriaisRecebidosResposta: String,
            val Modified: String,
            val NaoConformidade: String,
            val NotasFiscais: String,
            val OData__UIVersionString: String,
            val Observacoes: String,
            val ParentList: ParentList,
            val Properties: Properties,
            val RNCaberto: String,
            val RoleAssignments: RoleAssignments,
            val ServerRedirectedEmbedUri: Any,
            val ServerRedirectedEmbedUrl: String,
            val Status: String,
            val StatusdaRIR: String,
            val Title: String,
            val VersaoDaRIR: String,
            val Versions: Versions,
            val __metadata: Metadata
        ) {
            data class AttachmentFiles(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class ContentType(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class FieldValuesAsHtml(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class FieldValuesAsText(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class FieldValuesForEdit(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class File(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class FirstUniqueAncestorSecurableObject(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class Folder(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class GetDlpPolicyTip(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class LikedByInformation(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class ParentList(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class Properties(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class RoleAssignments(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class Versions(
                val __deferred: Deferred
            ) {
                data class Deferred(
                    val uri: String
                )
            }

            data class Metadata(
                val etag: String,
                val id: String,
                val type: String,
                val uri: String
            )
        }
    }
}

fun RirListDto.toRirList(): List<Rir> {
    return d.results.map {
        Rir(
            localId = null,
            apiId = it.Id,
            title = it.Title,
            fornecedor = it.Fornecedor,
            horarioDaInspecao = it.HorarioDaInspecao,
            dataDeRecebimento = it.DataDeRecebimento,
            categoria = it.Categoria,
            conferenciaTecnica = it.ConferenciaTecnica,

            )
    }
}