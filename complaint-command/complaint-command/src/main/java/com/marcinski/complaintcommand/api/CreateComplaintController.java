package com.marcinski.complaintcommand.api;

import com.marcinski.complaintcommand.api.dto.BaseResponse;
import com.marcinski.complaintcommand.infrastructure.command.CreateComplaintCommand;
import com.marcinski.complaintcommand.infrastructure.handler.CommandDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class CreateComplaintController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> createComplaint(@Valid @RequestBody CreateComplaintCommand command,
                                                        HttpServletRequest httpServletRequest) {
        var productId = command.getComplaintProductId().toString();
        var reporterName = command.getReporterName();
        var id = UUID.nameUUIDFromBytes((reporterName + productId).getBytes());
        command.setId(id.toString());
        command.setIpAddress(getClientIp(httpServletRequest));

        commandDispatcher.send(command);
        return new ResponseEntity<>(new BaseResponse(id.toString()), HttpStatus.CREATED);
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0]; // First IP in list is original client
        }
        return request.getRemoteAddr();
    }
}
