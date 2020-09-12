package com.rccsilva.kafkaflow

import com.fasterxml.jackson.databind.ObjectMapper
import com.rccsilva.kafkaflow.domain.StreamData
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate

@SpringBootTest
class BaseListenerTests {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Test
    fun test() {
        val streamData = StreamData(data = "message", topics = listOf(
                "kafka_flow.user_handler.second",
                "kafka_flow.user_handler.third"
            ),
            topicIndex = 0
        )
        kafkaTemplate.send(
            "kafka_flow.user_handler.first",
            objectMapper.writeValueAsString(streamData)
        )
        kafkaTemplate.flush()
        Thread.sleep(10000)
    }
}