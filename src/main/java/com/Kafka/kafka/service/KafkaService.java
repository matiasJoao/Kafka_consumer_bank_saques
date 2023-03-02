package com.Kafka.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

public interface KafkaService {

     void receberMensagem(ConsumerRecord<String, String> consumerRecord);

     void resetarQuantidadeSaque();
}
