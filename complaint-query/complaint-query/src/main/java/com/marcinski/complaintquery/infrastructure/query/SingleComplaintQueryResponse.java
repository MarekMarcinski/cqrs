package com.marcinski.complaintquery.infrastructure.query;

import com.marcinski.complaintquery.domain.Complaint;
import lombok.Value;

@Value
public class SingleComplaintQueryResponse extends QueryResponse {
    Complaint complaint;
}
