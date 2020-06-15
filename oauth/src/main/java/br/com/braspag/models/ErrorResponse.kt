package br.com.braspag.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val errorCode: String? = null,

    @SerializedName("error_description")
    val errorDescription: String? = null
)
