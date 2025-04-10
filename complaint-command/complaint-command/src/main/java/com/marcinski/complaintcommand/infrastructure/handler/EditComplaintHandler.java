package com.marcinski.complaintcommand.infrastructure.handler;

import com.marcinski.complaintcommand.infrastructure.ComplaintEventSourcingHandler;
import com.marcinski.complaintcommand.infrastructure.command.EditComplaintContentsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class EditComplaintHandler implements CommandHandler<EditComplaintContentsCommand> {

    private final ComplaintEventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(EditComplaintContentsCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeContents(command.getContents());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public Class<EditComplaintContentsCommand> getCommandClass() {
        return EditComplaintContentsCommand.class;
    }
}
