package com.swisslog.crud.config

import com.swisslog.crud.entity.Employees
import com.swisslog.crud.kafka.KafkaProperties
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
class KafkaConfig(var kafkaProperties: KafkaProperties) {

    @Qualifier(value = "crudConsumer")
    @Bean(name = ["crudConsumer"])
    fun employeeConsumer(): ConcurrentKafkaListenerContainerFactory<String, Employees> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Employees>()
        factory.consumerFactory = DefaultKafkaConsumerFactory<String, Employees>(
                kafkaProperties.kafkaJsonConsumerConfig(),
                StringDeserializer(),
                JsonDeserializer(Employees::class.java))
        return factory
    }

    @Qualifier(value = "crudProducer")
    @Bean(name = ["crudProducer"])
    fun employeeProducer(): KafkaTemplate<String, Employees> {
        return KafkaTemplate<String, Employees>(
                DefaultKafkaProducerFactory<String, Employees>(
                        kafkaProperties.kafkaJsonProducerConfig(),
                        StringSerializer(),
                        JsonSerializer<Employees>()))
    }
}