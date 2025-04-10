package com.marcinski.complaintquery.infrastructure.query;

import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value
public class FindAllComplaintQuery extends BaseQuery {
    Pageable pageable;
}
