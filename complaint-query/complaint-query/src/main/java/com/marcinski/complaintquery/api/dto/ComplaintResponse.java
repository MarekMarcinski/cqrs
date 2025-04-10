package com.marcinski.complaintquery.api.dto;


import java.time.LocalDate;

public record ComplaintResponse(String id, String complaintProductId, String reporterName, String contents,
                                LocalDate creationDate, String country, Integer reportCounter) {
}