package com.marcinski.complaintquery.api.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public record ListComplaintResponse(@JsonValue List<ComplaintResponse> complaintResponses) {
}
