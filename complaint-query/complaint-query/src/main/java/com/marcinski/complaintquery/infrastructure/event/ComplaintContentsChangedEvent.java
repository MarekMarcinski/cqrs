package com.marcinski.complaintquery.infrastructure.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ComplaintContentsChangedEvent extends BaseEvent {
    private String contents;
}
