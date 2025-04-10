package com.marcinski.complaintcommand.infrastructure;

import com.marcinski.complaintcommand.domain.BaseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ComplaintEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    void produce(String topic, BaseEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
