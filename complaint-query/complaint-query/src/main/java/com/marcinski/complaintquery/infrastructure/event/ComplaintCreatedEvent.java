package com.marcinski.complaintquery.infrastructure.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ComplaintCreatedEvent extends BaseEvent {
    private UUID complaintProductId;
    private String contents;
    private LocalDate createdDate;
    private String reporterName;
    private String ipAddress;
}
