package com.rccsilva.kafkaflow.interfaces

import com.rccsilva.kafkaflow.domain.StreamData

interface IHandler {
    val topic: String
    fun handler(data: StreamData): StreamData
}