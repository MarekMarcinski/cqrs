package com.marcinski.complaintcommand.infrastructure.handler;

import com.marcinski.complaintcommand.aggregate.ComplaintAggregate;
import com.marcinski.complaintcommand.infrastructure.ComplaintEventSourcingHandler;
import com.marcinski.complaintcommand.infrastructure.command.CreateComplaintCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CreateComplaintHandler implements CommandHandler<CreateComplaintCommand> {

    private final ComplaintEventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(CreateComplaintCommand command) {
        var aggregate = new ComplaintAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public Class<CreateComplaintCommand> getCommandClass() {
        return CreateComplaintCommand.class;
    }
}
