package com.marcinski.complaintquery.infrastructure;

import com.marcinski.complaintquery.infrastructure.event.ComplaintContentsChangedEvent;
import com.marcinski.complaintquery.infrastructure.event.ComplaintCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintEventService {

    private final ComplaintEventHandler eventHandler;

    @KafkaListener(topics = "ComplaintCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ComplaintCreatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "ComplaintContentsChangedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ComplaintContentsChangedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
