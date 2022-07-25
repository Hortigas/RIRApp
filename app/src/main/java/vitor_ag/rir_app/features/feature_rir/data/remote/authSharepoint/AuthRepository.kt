package vitor_ag.rir_app.features.feature_rir.data.remote.authSharepoint

import okhttp3.OkHttpClient

interface AuthRepository {
    val token: String
    val client: OkHttpClient
    fun passToken(): String
    fun fetchAuthToken()
}