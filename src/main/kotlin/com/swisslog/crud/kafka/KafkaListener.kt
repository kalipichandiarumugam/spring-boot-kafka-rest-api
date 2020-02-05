package com.swisslog.crud.kafka

import com.swisslog.crud.entity.Employees
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class KafkaListener(@Qualifier(value = "crudProducer")
                    val employeeProducerTemplate: KafkaTemplate<String, Employees>) {

    @KafkaListener(
            topics = ["NewEmployee"],
            containerFactory = "crudConsumer",
            groupId = "employees001")
    fun handle(emp: ConsumerRecord<String, Employees>){
        var employee = emp.value()

        employeeProducerTemplate.send("ConsumerEmployee", UUID.randomUUID().toString(), employee)
    }
}