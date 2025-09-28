package com.example.data.database

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.example.data.util.constant.MONGO_DATABASE_NAME

object DatabaseFactory {

    fun create() : MongoDatabase {
        val connectionString = System.getenv("MONGO_URI")
            ?: throw IllegalArgumentException("MONGO_URI not set")
        val mongoClient = MongoClient.create(connectionString)
        return mongoClient.getDatabase(MONGO_DATABASE_NAME)
    }

}