package com.marcinski.complaintcommand.infrastructure.handler;

import com.marcinski.complaintcommand.domain.BaseCommand;

interface CommandHandler<T extends BaseCommand> {
    void handle(T command);

    Class<T> getCommandClass();
}
