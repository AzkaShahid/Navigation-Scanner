package com.app.models.sn

data class MessageAnim (
    val text: String,
    val isUser: Boolean,
    val isTyping: Boolean = false // default false
)
