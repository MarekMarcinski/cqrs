package com.marcinski.complaintquery.infrastructure.query;

import com.marcinski.complaintquery.domain.Complaint;
import lombok.Value;
import org.springframework.data.domain.Page;

@Value
public class ListComplaintQueryResponse extends QueryResponse {
    Page<Complaint> complaints;
}
