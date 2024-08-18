package com.iucoding.ktorchat.data.model

interface MessageDataSource {

    suspend fun getAllMessages(): List<Message>

    suspend fun insertMessages(message: Message)
}
