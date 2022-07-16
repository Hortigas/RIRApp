package vitor_ag.rir_app.data.remote

import okhttp3.OkHttpClient

interface AuthRepository {
    val token: String
    val client: OkHttpClient
    fun passToken(): String
    fun fetchAuthToken()
}