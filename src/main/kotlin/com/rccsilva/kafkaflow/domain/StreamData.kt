package com.rccsilva.kafkaflow.domain

data class StreamData (
    val data: String,
    val topics: List<String>,
    var topicIndex: Int
) {
    val hasNext: Boolean get() = topicIndex < topics.size

    fun nextTopic(): String {
        val topic = topics[topicIndex]
        topicIndex++
        return topic
    }
}