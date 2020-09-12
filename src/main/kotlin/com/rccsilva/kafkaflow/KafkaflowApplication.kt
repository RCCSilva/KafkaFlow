package com.rccsilva.kafkaflow

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaflowApplication

fun main(args: Array<String>) {
	runApplication<KafkaflowApplication>(*args)
}
