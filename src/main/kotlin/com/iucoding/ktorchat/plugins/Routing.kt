package com.iucoding.ktorchat.plugins

import com.iucoding.ktorchat.room.RoomController
import com.iucoding.ktorchat.routes.getAllMessages
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    routing {
        getAllMessages(roomController)
    }
}
