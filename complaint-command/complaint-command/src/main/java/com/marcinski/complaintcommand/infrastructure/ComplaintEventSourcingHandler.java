package com.marcinski.complaintcommand.infrastructure;

import com.marcinski.complaintcommand.aggregate.AggregateRoot;
import com.marcinski.complaintcommand.aggregate.ComplaintAggregate;
import com.marcinski.complaintcommand.domain.BaseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ComplaintEventSourcingHandler {

    private final ComplaintEventStore eventStore;

    public void save(AggregateRoot aggregate) {
        eventStore.saveEvent(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    public ComplaintAggregate getById(String id) {
        var aggregate = new ComplaintAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

}
