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

internal expect val ApplicationDispatcher: CoroutineDispatcher

class ApiService {
    private val json = Json {
        ignoreUnknownKeys = true
        useArrayPolymorphism = true
        isLenient = true
    }
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }
    private var address = Url("https://postman-echo.com/get?foo1=bar1&foo2=bar2")

    fun about(callback: (String) -> Unit) {
        GlobalScope.apply {
            launch(ApplicationDispatcher) {
                val result: String = httpClient.get {
                    url(this@ApiService.address.toString())
                }
                callback(result)
            }
        }
    }
}