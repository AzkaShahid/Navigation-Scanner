package com.app.models.chatRequest

data class ChatRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<MessageRequest>
)

data class MessageRequest(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: MessageRequest
)