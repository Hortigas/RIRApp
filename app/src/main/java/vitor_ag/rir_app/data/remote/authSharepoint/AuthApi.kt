package vitor_ag.rir_app.data.remote.authSharepoint

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/9f250032-dc8e-488c-b733-d6b3ef6e8685/tokens/OAuth/2")
    fun getToken(
        @Body authRequestBody: RequestBody
    ): Call<AuthResponseBody>
}
