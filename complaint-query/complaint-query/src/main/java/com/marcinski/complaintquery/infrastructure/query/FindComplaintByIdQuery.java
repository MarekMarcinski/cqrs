package com.marcinski.complaintquery.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
public class FindComplaintByIdQuery extends BaseQuery {
    String id;

    public FindComplaintByIdQuery() {
    }
}
