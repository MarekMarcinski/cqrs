package com.marcinski.complaintcommand.api.dto;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime timestamp, String errorMessage) {
}
