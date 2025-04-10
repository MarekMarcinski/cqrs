package com.marcinski.complaintquery.api.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.domain.Page;

public record ListComplaintResponse(@JsonValue Page<ComplaintResponse> complaintResponses) {
}
