package vitor_ag.rir_app.data.remote

data class AuthResponseBody(
    val token_type: String,
    val expires_in: String,
    val not_before: String,
    val expires_on: String,
    val resource: String,
    val access_token: String
)