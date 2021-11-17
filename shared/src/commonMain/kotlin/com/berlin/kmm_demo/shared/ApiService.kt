package com.berlin.kmm_demo.shared

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.KSerializer
import kotlin.native.concurrent.*
import kotlinx.serialization.decodeFromString

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ApiService {
    // Moving the Json formatter inside the `KotlinxSerializer` fixed the issue on iOS
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    useArrayPolymorphism = true
                    isLenient = true
                })
            }
        }
    private var address = Url("https://postman-echo.com/get?name=Momox&address=Berlin")

    fun about(callback: (String) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = httpClient.get {
                    url(this@ApiService.address.toString())
                }
                val postmanResponse = Json { ignoreUnknownKeys = true }.decodeFromString<PostmanResponse>(result)
                callback(postmanResponse.args.address)
            }
        }
    }
}