package br.com.braspag.models

data class ClientResult <T>(
    var result: T?,
    var statusCode: HttpStatusCode,
    var error: ErrorResponse? = null
)