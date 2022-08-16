package com.harbourspace.unsplash.model

import com.google.firebase.Timestamp

data class Message(
    val username: String,
    val content: String,
    val timestamp: Timestamp
)