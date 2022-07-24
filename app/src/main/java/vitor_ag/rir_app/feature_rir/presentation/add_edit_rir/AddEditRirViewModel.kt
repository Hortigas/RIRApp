package vitor_ag.rir_app.feature_rir.presentation.add_edit_rir

import android.content.Context
import android.media.ExifInterface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import vitor_ag.rir_app.data.Photo
import vitor_ag.rir_app.data.Rir
import vitor_ag.rir_app.data.RirRepository
import vitor_ag.rir_app.ui.add_edit_rir.components.compose.ValidationListItem
import vitor_ag.rir_app.util.GpsConverter
import vitor_ag.rir_app.util.LocationLiveData
import vitor_ag.rir_app.util.UiEvent
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditRirViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: RirRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val rirs = repository.getRirs()

    var shouldShowPhotoDialog by mutableStateOf(false)
    var shouldShowCreateRirDialog by mutableStateOf(false)
    var shouldShowCameraScanner by mutableStateOf(false)

    //Dados gerais
    var fornecedor by mutableStateOf("")
        private set
    val locais = mutableStateListOf<String>()
    var dataDeRecebimento by mutableStateOf("")
        private set
    var categoria by mutableStateOf("")
        private set

    //Documentação
    var documentacao = mutableStateListOf<ValidationListItem>(
        ValidationListItem(
            "Certificado de qualidade", "OK"
        ),
        ValidationListItem(
            "Certificado de procedência (para a madeira)", "OK"
        ),
        ValidationListItem(
            "Manual ou ficha técnica do produto / equipamento", "OK"
        )
    )

    //Notas fiscais
    var notaFiscal by mutableStateOf("")
        private set
    val notas = mutableStateListOf<String>()
    var itensInspecionados by mutableStateOf("")
        private set
    var observacoes by mutableStateOf("")
        private set

    //Conferência técnica
    val conferenciaTecnica = mutableStateListOf<ValidationListItem>(
        ValidationListItem(
            "A carga está devidamente acomodada no transporte?",
            "OK"
        ),
        ValidationListItem(
            "Os itens estão sem ocorrência de avarias?",
            "OK"
        ),
        ValidationListItem(
            "As quantidades dos itens estão de acordo com o pedido de compra?",
            "OK"
        ),
        ValidationListItem(
            "A carga está acompanhada do Certificado de Qualidade / Ensaios? O mesmo atende as normas / ET?",
            "OK"
        ),
        ValidationListItem(
            "As características do material, equipamento e/ou ferramenta atendem ao especificado conforme norma técnica?",
            "OK"
        ),
        ValidationListItem(
            "As características do material, equipamento e/ou ferramenta atendem ao especificado em projeto executivo / ET?",
            "OK"
        ),
        ValidationListItem(
            "O local para armazenamento esta plano, limpo e nivelado? O material foi armazenado com altura segura do solo(sobre calço de madeira / palet) afim de evitar danos?",
            "OK"
        )
    )

    //Aprovação
    var materiaisRecebidos by mutableStateOf("")
        private set
    var naoConformidade by mutableStateOf("")
        private set
    var statusRir by mutableStateOf("")
        private set

    //Registro fotográfico
    private val locationLiveData = LocationLiveData(context)
    fun startLocationUpdates() {
        locationLiveData.startLocationUpdates()
    }

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH)
    private val uniqueIdGen = SimpleDateFormat("HHmmssSSS", Locale.ENGLISH)
    val photoGallery = mutableStateListOf<Photo>()
    val photoCategoryList = listOf(
        "Recebimento do Material",
        "Descarga do Material",
        "Armazenamento do Material",
        "Inspeção do Material"
    )
    var selectedPhotoCategory by mutableStateOf("")

    init {
        val fileImage = File("${context.cacheDir}/photos")
        val images = fileImage.listFiles()
        if (!images.isNullOrEmpty())
            images.forEach {
                val exif = ExifInterface(it.toString())

                val latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
                val latitude = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
                    ?.let { (if (latitudeRef == "E") 1 else -1) * GpsConverter.convertToDouble(it) }

                val longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)
                val longitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
                    ?.let { (if (longitudeRef == "N") 1 else -1) * GpsConverter.convertToDouble(it) }

                photoGallery.add(
                    Photo(
                        uri = it.toString(),
                        createdDate = exif.getAttribute(ExifInterface.TAG_DATETIME).toString(),
                        gps = "${latitude.toString()},${longitude.toString()}",
                        category = exif.getAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION).toString()
                    )
                )
            }
    }

    fun onEvent(event: AddEditRirEvent) {
        when (event) {
            is AddEditRirEvent.OnOpenNavBar -> {
                sendUiEvent(UiEvent.ShowNavBar)
            }
            is AddEditRirEvent.OnFornecedorChange -> {
                fornecedor = event.fornecedor
            }
            is AddEditRirEvent.OnAddLocal -> {
                locais.add(event.local)
                locais.sort()
            }
            is AddEditRirEvent.OnRemoveLocal -> {
                locais.remove(event.local)
            }
            is AddEditRirEvent.OnDataDeRecebimentoChange -> {
                dataDeRecebimento = event.dataDeRecebimento
            }
            is AddEditRirEvent.OnCategoriaChange -> {
                categoria = event.categoria
            }
            is AddEditRirEvent.OnDocumentacaoChange -> {
                documentacao[event.id] =
                    ValidationListItem(documentacao[event.id].title, event.selectedOption)
            }
            is AddEditRirEvent.OnOpenScanner -> {
                shouldShowCameraScanner = true
            }
            is AddEditRirEvent.OnScanNotaFiscal -> {
                notaFiscal = event.text
                shouldShowCameraScanner = false
            }
            is AddEditRirEvent.OnNotaChange -> {
                notaFiscal = event.text
            }
            is AddEditRirEvent.OnAddNota -> {
                if (notaFiscal.isNotBlank()) {
                    notas.add(notaFiscal)
                    notaFiscal = ""
                }
            }
            is AddEditRirEvent.OnRemoveNota -> {
                notas.remove(event.nota)
            }
            is AddEditRirEvent.OnItensInspecionadosChange -> {
                itensInspecionados = event.itensInspecionados
            }
            is AddEditRirEvent.OnObservacoesChange -> {
                observacoes = event.observacoes
            }
            is AddEditRirEvent.OnConferenciaTecnicaChange -> {
                conferenciaTecnica[event.id] =
                    ValidationListItem(conferenciaTecnica[event.id].title, event.selectedOption)
            }
            is AddEditRirEvent.OnMateriaisRecebidosChange -> {
                materiaisRecebidos = event.aprovacao
            }
            is AddEditRirEvent.OnNaoConformidadeChange -> {
                naoConformidade = event.aprovacao
            }
            is AddEditRirEvent.OnStatusRirChange -> {
                statusRir = event.statusRir
            }
            is AddEditRirEvent.OnPhotoTaken -> {
                val fileName = event.photo.toString().split("/").last()
                val path = File(context.cacheDir, "photos/$fileName").path
                val createdDate = dateFormat.format(Date())
                val latitude = locationLiveData.value?.latitude
                val longitude = locationLiveData.value?.longitude
                val exif = ExifInterface(path)
                exif.setAttribute(
                    ExifInterface.TAG_DATETIME,
                    createdDate
                )
                if (latitude != null && longitude != null) {
                    exif.setAttribute(
                        ExifInterface.TAG_GPS_LATITUDE,
                        GpsConverter.convertToDMS(latitude.toDouble())
                    )
                    exif.setAttribute(
                        ExifInterface.TAG_GPS_LATITUDE_REF,
                        GpsConverter.latitudeRef(latitude.toDouble())
                    );
                    exif.setAttribute(
                        ExifInterface.TAG_GPS_LONGITUDE,
                        GpsConverter.convertToDMS(longitude.toDouble())
                    )
                    exif.setAttribute(
                        ExifInterface.TAG_GPS_LONGITUDE_REF,
                        GpsConverter.longitudeRef(longitude.toDouble())
                    );
                }
                exif.saveAttributes()
                photoGallery.add(
                    Photo(
                        uri = path,
                        createdDate = createdDate,
                        gps = "$latitude,$longitude"
                    )
                )
                selectedPhotoCategory = when (photoGallery.size) {
                    1 -> photoCategoryList[0]
                    2 -> photoCategoryList[1]
                    3 -> photoCategoryList[2]
                    else -> photoCategoryList[3]
                }
                shouldShowPhotoDialog = true
            }
            is AddEditRirEvent.OnRemovePhoto -> {
                File(photoGallery[event.index].uri).delete()
                photoGallery.removeAt(event.index)
            }
            is AddEditRirEvent.OnPhotoCategoryChange -> {
                selectedPhotoCategory = event.category
            }
            is AddEditRirEvent.OnPhotoCategoryConfirm -> {
                val photo = photoGallery.last()
                val exif = ExifInterface(photo.uri)
                exif.setAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION, event.category)
                exif.saveAttributes()
                photo.category = event.category
                photoGallery[photoGallery.lastIndex] = photo
            }
            is AddEditRirEvent.OnOpenPopupRir -> {
                shouldShowCreateRirDialog = true
            }
            is AddEditRirEvent.OnCreateRir -> {
                var uniqueID = uniqueIdGen.format(Date()).toInt()
//                val photos = FileManeger.savePhotosOnStorage(
//                    context = context,
//                    uniqueId = uniqueID,
//                    photos = photoGallery
//                )
                viewModelScope.launch {
                    repository.insertRir(
                        Rir(
                            id = uniqueID,
                            title = "",
                            fornecedor = fornecedor,
                            local = locais.joinToString(),
                            dataDeRecebimento = dataDeRecebimento,
                            categoria = categoria,
                            documentacaoString = documentacao.joinToString("|") { it.selectedOption },
                            notasFiscaisString = Gson().toJson(
                                notas.map { NotaFiscal(Nota = it) }
                            ),
                            itensInspecionados = itensInspecionados,
                            observacoes = observacoes,
                            conferenciaTecnicaString = conferenciaTecnica.joinToString("|") { "${it.title}#${it.selectedOption}" },
                            materiaisRecebidos = materiaisRecebidos,
                            naoConformidade = naoConformidade,
                            statusDaRir = statusRir,
                            horarioDaInspecao = dateFormat.format(Date()).split(".").first(),
                            photos = photoGallery
                        )
                    )
                    clearFields()
                }
            }
        }
    }

    private fun clearFields() {
        photoGallery.clear()
        fornecedor = ""
        locais.clear()
        dataDeRecebimento = ""
        categoria = ""
        documentacao.forEachIndexed { index, item ->
            documentacao[index] = ValidationListItem(item.title, "OK")
        }
        notas.clear()
        itensInspecionados = ""
        observacoes = ""
        materiaisRecebidos = ""
        naoConformidade = ""
        statusRir = ""
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch { _uiEvent.send(event) }
    }
}