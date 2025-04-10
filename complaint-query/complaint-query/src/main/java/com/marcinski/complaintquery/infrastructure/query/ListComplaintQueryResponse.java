package com.marcinski.complaintquery.infrastructure.query;

import com.marcinski.complaintquery.domain.Complaint;
import lombok.Value;

import java.util.List;

@Value
public class ListComplaintQueryResponse extends QueryResponse {
    List<Complaint> complaints;
}
