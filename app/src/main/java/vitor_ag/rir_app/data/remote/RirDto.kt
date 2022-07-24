package vitor_ag.rir_app.data.remote

import com.google.gson.annotations.SerializedName

class RirDto {
    @SerializedName("d")
    var d: D? = null
}

class D {
    @SerializedName("results")
    var results: List<RirDtoData>? = null

}

class RespDto {
    @SerializedName("d")
    var d: RirDtoData? = null
}