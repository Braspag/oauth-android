package br.com.braspag

import br.com.braspag.internal.network.OAuthClient
import br.com.braspag.models.ClientResult
import br.com.braspag.models.OAuthEnvironment
import br.com.braspag.models.OAuthResponse

class OAuth(
    private val clientId: String,
    private val clientSecret: String,
    private val environment: OAuthEnvironment = OAuthEnvironment.SANDBOX
) {
    fun getToken(callback: (model: ClientResult<OAuthResponse>) -> Unit) {
        val client = OAuthClient(clientId, clientSecret, environment)
        client.getToken {
            callback.invoke(it)
        }
    }
}