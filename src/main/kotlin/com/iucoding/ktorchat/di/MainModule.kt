package com.iucoding.ktorchat.di

import com.iucoding.ktorchat.data.MessageDataSourceImpl
import com.iucoding.ktorchat.data.model.MessageDataSource
import com.iucoding.ktorchat.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single<CoroutineDatabase> {
        KMongo.createClient()
            .coroutine
            .getDatabase("message_db_yt")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single<RoomController> {
        RoomController(get())
    }
}
