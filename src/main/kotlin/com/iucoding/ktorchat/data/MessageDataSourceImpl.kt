package com.iucoding.ktorchat.data

import com.iucoding.ktorchat.data.model.Message
import com.iucoding.ktorchat.data.model.MessageDataSource
import org.litote.kmongo.coroutine.CoroutineDatabase

class MessageDataSourceImpl(
    db: CoroutineDatabase
) : MessageDataSource {

    private val messages = db.getCollection<Message>()

    override suspend fun getAllMessages(): List<Message> {
        return messages.find()
            .descendingSort(Message::timestamp)
            .toList()
    }

    override suspend fun insertMessages(message: Message) {
        messages.insertOne(message)
    }
}
