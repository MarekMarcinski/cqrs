package com.marcinski.complaintcommand.infrastructure;


import com.marcinski.complaintcommand.aggregate.ComplaintAggregate;
import com.marcinski.complaintcommand.domain.BaseEvent;
import com.marcinski.complaintcommand.domain.EventModel;
import com.marcinski.complaintcommand.domain.EventStoreRepository;
import com.marcinski.complaintcommand.exceptions.AggregateNotFoundException;
import com.marcinski.complaintcommand.exceptions.ConcurrencyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintEventStore {

    private final ComplaintEventProducer eventProducer;

    private final EventStoreRepository eventStoreRepository;

    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .eventType(event.getClass().getTypeName())
                    .aggregateType(ComplaintAggregate.class.getTypeName())
                    .eventData(event)
                    .version(version)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException(aggregateId);
        }
        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }

    public List<String> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll();
        if (eventStream == null || eventStream.isEmpty()) {
            throw new IllegalStateException("Could not find any events");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).collect(Collectors.toList());
    }
}
