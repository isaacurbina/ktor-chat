package com.iucoding.ktorchat

import com.iucoding.ktorchat.di.mainModule
import com.iucoding.ktorchat.plugins.configureMonitoring
import com.iucoding.ktorchat.plugins.configureRouting
import com.iucoding.ktorchat.plugins.configureSecurity
import com.iucoding.ktorchat.plugins.configureSerialization
import com.iucoding.ktorchat.plugins.configureSockets
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
