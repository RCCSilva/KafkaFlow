package com.rccsilva.kafkaflow.listeners

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rccsilva.kafkaflow.domain.StreamData
import com.rccsilva.kafkaflow.interfaces.IHandler
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class BaseListener (
    private val objectMapper: ObjectMapper,
    private val handlers: List<IHandler>,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val groupedHandler = handlers.associateBy { it.topic }

    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topicPattern = "kafka_flow.user_handler.*", concurrency = "3")
    fun listen(@Header(name = "kafka_receivedTopic") topic: String, message: String) {
        logger.info("Received message from $topic message $message")

        val handler = groupedHandler[topic] ?:
            throw IllegalArgumentException("There is no handler registered for $topic")

        val streamData = objectMapper.readValue<StreamData>(message)

        val responseStreamData = handler.handler(streamData)

        if (responseStreamData.hasNext) {
            val nextTopic = responseStreamData.nextTopic()
            kafkaTemplate.send(nextTopic, objectMapper.writeValueAsString(responseStreamData))
        }
    }
}