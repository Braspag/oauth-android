package br.com.braspag.internal.network

import br.com.braspag.models.HttpStatusCode
import br.com.braspag.models.OAuthEnvironment
import br.com.braspag.extensions.toStatusCode
import br.com.braspag.models.OAuthResponse
import br.com.braspag.models.ClientResult
import br.com.braspag.models.ErrorResponse
import com.google.gson.Gson
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class OAuthClient(
    private val clientId: String,
    private val clientSecret: String,
    private val environment: OAuthEnvironment = OAuthEnvironment.SANDBOX

) {

    fun getToken(callback: (model: ClientResult<OAuthResponse>) -> Unit) {
        val webClient = WebClient(
            getEnvironmentUrl(environment)
        )

        val credentials: String = Credentials.basic(clientId, clientSecret)

        val call = webClient.createService(OAuthApi::class.java).getToken(credentials)

        call.enqueue(object : Callback<OAuthResponse> {
            override fun onFailure(call: Call<OAuthResponse>, t: Throwable) {
                t.localizedMessage?.let {
                    callback.invoke(
                        ClientResult(
                            result = null,
                            statusCode = HttpStatusCode.Unknown,
                            error = ErrorResponse(
                                errorCode = null,
                                errorDescription = it
                            )
                        )
                    )
                }
            }

            override fun onResponse(
                call: Call<OAuthResponse>,
                response: Response<OAuthResponse>
            ) {
                when (response.isSuccessful) {
                    true -> {
                        callback.invoke(
                            ClientResult(
                                result = response.body(),
                                statusCode = response.code().toStatusCode()
                            )
                        )
                    }

                    false -> callback.invoke(
                        ClientResult(
                            result = null,
                            statusCode = response.code().toStatusCode(),
                            error = Gson().fromJson(
                                response.errorBody()?.string(),
                                ErrorResponse::class.java
                            )
                        )
                    )
                }
            }
        })
    }

    private fun getEnvironmentUrl(environment: OAuthEnvironment): String {
        return if (environment == OAuthEnvironment.SANDBOX)
            "https://authsandbox.braspag.com.br/"
        else
            "https://auth.braspag.com.br/"
    }
}