package com.iucoding.ktorchat.data

import com.iucoding.ktorchat.data.model.Message

interface MessageDataSource {

    suspend fun getAllMessages(): List<Message>

    suspend fun insertMessages(message: Message)
}
