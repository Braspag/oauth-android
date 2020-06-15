package br.com.braspag.app

import android.app.Activity
import android.os.Bundle
import br.com.braspag.OAuth
import br.com.braspag.models.OAuthEnvironment
import kotlinx.android.synthetic.main.activity_main.textView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oauth = OAuth(
            clientId = "CLIENT-ID",
            clientSecret = "CLIENT-SECRET",
            environment = OAuthEnvironment.SANDBOX
        )

        oauth.getToken {
            if (it.error != null) {
                textView.text = it.error?.errorDescription
            } else if (it.result != null) {
                textView.text = it.result?.accessToken
            }
        }
    }
}