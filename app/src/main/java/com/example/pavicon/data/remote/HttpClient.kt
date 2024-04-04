package com.example.pavicon.data.remote

import com.example.pavicon.common.Constants
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Ignore unknown keys in JSON
            })
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        engine {
            // Set timeout
            requestTimeout = 60000 // 5000 milliseconds = 5 seconds

        }
    }
    val baseUrl = Constants.baseUrl
}