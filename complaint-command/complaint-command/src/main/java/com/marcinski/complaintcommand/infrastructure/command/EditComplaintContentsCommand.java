package com.marcinski.complaintcommand.infrastructure.command;

import com.marcinski.complaintcommand.domain.BaseCommand;
import lombok.Data;

@Data
public class EditComplaintContentsCommand extends BaseCommand {
    private String contents;
}
