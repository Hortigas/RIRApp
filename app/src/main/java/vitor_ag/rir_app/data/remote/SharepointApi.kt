package vitor_ag.rir_app.data.remote

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface SharepointApi {
    @POST("/sites/{site}/_api/lists/getbytitle('{list}')/items")
    suspend fun postRir(
        @Path("site", encoded = true) site: String,
        @Path("list", encoded = true) list: String,
        @Body rir: RirDtoData,
    ): RespDto

    @GET("/sites/{site}/_api/lists/getbytitle('{list}')/items")
    suspend fun getRir(
        @Path("site", encoded = true) site: String,
        @Path("list", encoded = true) list: String,
        @Query("\$select") select: String?,
        @Query("\$top") top: Int?,
        @Query("\$orderby") orderBy: String?,
    ): RirDto

    @POST("/sites/{site}/_api/lists/getbytitle('{list}')/items({id})/AttachmentFiles/add(FileName='{fileName}')")
    suspend fun uploadAttachment(
        @Path("site", encoded = true) site: String,
        @Path("list", encoded = true) list: String,
        @Path("id", encoded = true) id: String,
        @Path("fileName", encoded = true) fileName: String,
        @Body fileContent: RequestBody
    ): ResponseBody
}
