package vitor_ag.rir_app.data.remote

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.HttpURLConnection


class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override var token: String = ""

    override val client: OkHttpClient = OkHttpClient().newBuilder()
        //.authenticator(AccessTokenAuthenticator())
        .addInterceptor(AccessTokenInterceptor(
            token = { passToken() },
            fetchAuthToken = { fetchAuthToken() }
        ))
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json;odata=verbose")
                    .addHeader("Content-Type", "application/json;odata=verbose")
                    .build()
            )
        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    override fun passToken(): String {
        return token
    }

    override fun fetchAuthToken() {
        try {
            val response = authApi.getToken(
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "grant_type",
                        "client_credentials"
                    )
                    .addFormDataPart(
                        "client_id",
                        "22abeb24-0121-4c3b-99c8-4648a644932f@9f250032-dc8e-488c-b733-d6b3ef6e8685"
                    )
                    .addFormDataPart(
                        "client_secret",
                        "Xz2gO3Vzf4ZyqKconwJHgW2rS9xENG9RD/J0bcUVWtc="
                    )
                    .addFormDataPart(
                        "resource",
                        "00000003-0000-0ff1-ce00-000000000000/andradegutierrez365.sharepoint.com@9f250032-dc8e-488c-b733-d6b3ef6e8685"
                    )
                    .build()
            ).execute()
            if (response.isSuccessful) {
                token = response.body()?.access_token ?: ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

class AccessTokenInterceptor(
    val token: () -> String,
    val fetchAuthToken: () -> Unit
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = newRequestWithAccessToken(chain.request(), token())
        val response: Response = chain.proceed(request)
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            synchronized(this) {
                fetchAuthToken()
                return chain.proceed(newRequestWithAccessToken(request, token()))
            }
        }
        return response
    }


    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}

//class AccessTokenAuthenticator : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        TODO("Not yet implemented")
//    }
//
//}

//override fun intercept(chain: Interceptor.Chain): Response {
//    val request: Request = newRequestWithAccessToken(chain.request(), token)
//    val response: Response = chain.proceed(request)
//    if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//        fetchAuthToken()
//        return chain.proceed(newRequestWithAccessToken(request, token))
//    }
//    return response
//}


//    override var accessToken: String? = null
//    override var expireTime: Int = -1
//

//    } catch (e: Exception) {
//        e.printStackTrace()
//        Resource.Error(e.message ?: "An unknown error occurred")
//    }
//}
//
//    override suspend fun getToken(): String? {
//        //val currentTimestamp = System.currentTimeMillis()
//        //if (currentTimestamp > expireTime) {}
//
//        when (val response = fetchAuthToken()) {
//            is Resource.Success -> {
//                accessToken = response.data!!.access_token
//                expireTime = response.data!!.expires_on.toInt()
//            }
//            is Resource.Error -> {
//                accessToken = null
//            }
//        }
//
//        return accessToken
//    }
//}
