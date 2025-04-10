package com.marcinski.complaintcommand.infrastructure.command;

import com.marcinski.complaintcommand.domain.BaseCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditComplaintContentsCommand extends BaseCommand {
    @NotBlank(message = "contents field can not be blank")
    private String contents;
}
