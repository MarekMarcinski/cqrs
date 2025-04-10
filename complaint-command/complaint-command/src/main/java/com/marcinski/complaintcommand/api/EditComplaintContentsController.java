package com.marcinski.complaintcommand.api;

import com.marcinski.complaintcommand.api.dto.BaseResponse;
import com.marcinski.complaintcommand.infrastructure.command.EditComplaintContentsCommand;
import com.marcinski.complaintcommand.infrastructure.handler.CommandDispatcher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class EditComplaintContentsController {

    private final CommandDispatcher commandDispatcher;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> restoreReadDb(@PathVariable(value = "id") String id,
                                                      @Valid @RequestBody EditComplaintContentsCommand command) {
        command.setId(id);
        commandDispatcher.send(command);
        return new ResponseEntity<>(new BaseResponse(id), HttpStatus.ACCEPTED);
    }
}
