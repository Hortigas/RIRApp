package vitor_ag.rir_app.data.remote

import com.google.gson.annotations.SerializedName

data class ResponseDao(
    @SerializedName("d")
    val resp: String
)