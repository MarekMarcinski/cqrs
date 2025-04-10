package com.marcinski.complaintquery.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class FindComplaintByIdQuery extends BaseQuery {
    String id;
}
