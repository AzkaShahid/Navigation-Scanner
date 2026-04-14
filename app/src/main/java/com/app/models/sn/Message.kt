package com.app.models.sn

data class Message(
    val text: String,
    val isUser: Boolean,
    val isTyping: Boolean = false
)
