package com.swisslog.crud.kafka


import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Component

@Component
class KafkaProperties {
    var bootstrap : String? = null
    var maxMessageSize : Int = 25000000



    fun kafkaJsonConsumerConfig(): MutableMap<String, Any> {
        return hashMapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to (bootstrap ?: "kafka:9092"),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
                ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG to maxMessageSize,
                ConsumerConfig.FETCH_MAX_BYTES_CONFIG to maxMessageSize,
                JsonDeserializer.TRUSTED_PACKAGES to "*")
    }

    fun kafkaJsonProducerConfig(): MutableMap<String, Any> {
        return hashMapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to (bootstrap ?: "kafka:9092"),
                ProducerConfig.MAX_REQUEST_SIZE_CONFIG to maxMessageSize,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
                ProducerConfig.ACKS_CONFIG to "all",
                ProducerConfig.RETRIES_CONFIG to 100000,
                ProducerConfig.MAX_BLOCK_MS_CONFIG to 600000,
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to true,
                ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION to 5)
    }
}