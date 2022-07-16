package vitor_ag.rir_app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import vitor_ag.rir_app.data.Rir

interface SharepointApi {
    @POST("/sites/{site}/_api/lists/getbytitle('{list}')/items")
    suspend fun postRir(
        @Path("site", encoded = true) site: String,
        @Path("list", encoded = true) list: String,
        @Body rir: Rir
    )

    @GET("/sites/{site}/_api/lists/getbytitle('{list}')/items")
    suspend fun getRir(
        @Path("site", encoded = true) site: String,
        @Path("list", encoded = true) list: String,
    )
}
