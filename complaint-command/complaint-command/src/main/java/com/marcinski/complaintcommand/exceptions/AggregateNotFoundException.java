package com.marcinski.complaintcommand.exceptions;

import lombok.Getter;

@Getter
public class AggregateNotFoundException extends RuntimeException {

    public AggregateNotFoundException(String id) {
        super("Aggregate not found for id " + id);
    }
}
