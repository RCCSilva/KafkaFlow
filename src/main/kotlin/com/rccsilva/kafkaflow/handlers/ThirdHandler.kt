package com.rccsilva.kafkaflow.handlers

import com.rccsilva.kafkaflow.domain.StreamData
import com.rccsilva.kafkaflow.interfaces.IHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ThirdHandler(
    @Value("\${kafka.topic.user-handler.third}")
    override val topic: String
): IHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handler(streamData: StreamData): StreamData {
        logger.info("Handler of $topic processed message ${streamData.data}")
        return streamData
    }
}