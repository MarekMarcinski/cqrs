package com.marcinski.complaintquery.api.dto;


public record ComplaintResponse(String id, String complaintProductId, String reporterName, String contents,
                                String country, Integer reportCounter) {
}
