package com.marcinski.complaintcommand.infrastructure.command;

import com.marcinski.complaintcommand.domain.BaseCommand;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateComplaintCommand extends BaseCommand {
    private String reporterName;
    private String contents;
    private UUID complaintProductId;
    private String ipAddress;
}
