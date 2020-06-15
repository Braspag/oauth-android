# OAUTH-ANDROID [![Status](https://travis-ci.com/Braspag/oauth-android.svg?branch=master)](https://travis-ci.com/Braspag/oauth-android) [ ![Download](https://api.bintray.com/packages/braspag/oauth/oauth/images/download.svg) ](https://bintray.com/braspag/oauth/oauth/_latestVersion)

Android Library para auxiliar na obtenção do AccessToken para OAuth Cielo

## Instalação

- Será necessário adicionar a seguinte dependência ao **build.gradle** do seu app module:

```kotlin
    implementation 'br.com.braspag:oauth:1.0.0'
```

- Ou baixar o pacote aar manualmente através da versão mais atual encontrada em [releases](https://github.com/Braspag/oauth-android/releases), adicionar esse pacote na pasta *libs* do seu app module e depois adicionar a seguinte linha ao *build.gradle* do seu app module:

```kotlin
    implementation files('libs/oauth-release.aar')
```

## Modo de uso

### Configuração

Para iniciar o cliente do SDK será necessário informar client id, client secret e o ambiente:

```kotlin
val oauth = OAuth(
    clientId = "CLIENT-ID",
    clientSecret = "CLIENT-SECRET",
    environment = OAuthEnvironment.SANDBOX
)
```

### Utilização

```kotlin
oauth.getToken {
    if (it.error != null) {
        textView.text = it.error?.errorDescription
    } else if (it.result != null) {
        textView.text = it.result?.accessToken
    }
}
```
