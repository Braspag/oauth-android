package br.com.braspag.internal.network

import br.com.braspag.models.OAuthResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

internal interface OAuthApi {
    @FormUrlEncoded
    @POST("oauth2/token")
    fun getToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grant_type: String = "client_credentials"
    ): Call<OAuthResponse>
}