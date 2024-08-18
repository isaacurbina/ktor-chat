package com.iucoding.ktorchat.plugins

import com.iucoding.ktorchat.room.RoomController
import com.iucoding.ktorchat.routes.chatSocket
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import org.koin.ktor.ext.inject
import java.time.Duration

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    val roomController by inject<RoomController>()
    routing {
        chatSocket(roomController)
    }
}
