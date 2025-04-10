package com.marcinski.complaintcommand.infrastructure.handler;

import com.marcinski.complaintcommand.domain.BaseCommand;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
//@RequiredArgsConstructor
public class CommandDispatcher {

    private final Set<CommandHandler<? extends BaseCommand>> handlers;

    public CommandDispatcher(Set<CommandHandler<? extends BaseCommand>> handlers) {
        this.handlers = handlers;
    }


    public void send(BaseCommand command) {
        var commandHandlerMethod = handlers.stream()
                .filter(c -> c.getCommandClass()
                        .equals(command.getClass()))
                .map(CommandHandler.class::cast)
                .findFirst()
                .orElse(null); // throw
        commandHandlerMethod.handle(command);
    }
}
