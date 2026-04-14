package com.app.respository

import com.app.models.chatRequest.ChatRequest
import com.app.models.chatRequest.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIApi {

    @POST("v1/chat/completions")
    suspend fun getChatResponse(
        @Header("Authorization") auth: String,
        @Body request: ChatRequest
    ): ChatResponse
}