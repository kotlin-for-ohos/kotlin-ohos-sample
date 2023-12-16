import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Ohos
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@JsExport
class HelloJs {

    fun sayHello() {
        println("Hello Ohos from Kotlin!!")

        HiLog.info(10086, "kjs", "Hello from Kotlin js with HiLog!!!!!!")

        GlobalScope.launch {
            try {
                val client = HttpClient(Ohos) {
                    install(ContentNegotiation) {
                        json(Json {
                           ignoreUnknownKeys = true
                        })
                    }
                }

                val response = client.get("https://api.github.com/users/bennyhuo")
                val gitUser = response.body<GitUser>()
                HiLog.info(10000, "kjs", "response: $gitUser")
            } catch (e: Exception) {
                HiLog.info(10000, "kjs", "error: $e")
            }

        }
    }
}

@JsExport
@Serializable
data class GitUser(
    val login: String,
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String
)
