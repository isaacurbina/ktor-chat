package com.iucoding.ktorchat.room

import com.iucoding.ktorchat.data.MessageDataSource
import com.iucoding.ktorchat.data.model.Message
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messageDataSource: MessageDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()

    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if (members.containsKey(username)) {
            throw MemberAlreadyExistsException()
        }
        members[username] = Member(
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }

    suspend fun sendMessage(username: String, message: String) {
        val messageEntity = Message(
            text = message,
            username = username,
            timestamp = System.currentTimeMillis()
        )
        val parsedMessage = Json.encodeToString(messageEntity)
        messageDataSource.insertMessages(messageEntity)
        members.values.forEach { member ->
            member.socket.send(
                frame = Frame.Text(parsedMessage)
            )
        }
    }

    suspend fun getAllMessages(): List<Message> {
        return messageDataSource.getAllMessages()
    }

    suspend fun tryDisconnect(username: String) {
        if (members.containsKey(username)) {
            members[username]?.socket?.close()
            members.remove(username)
        }
    }
}
