package com.marcinski.complaintquery.infrastructure.handler;

import com.marcinski.complaintquery.infrastructure.query.BaseQuery;
import com.marcinski.complaintquery.infrastructure.query.QueryResponse;

interface QueryHandler<T extends BaseQuery, O extends QueryResponse> {
    O handle(T query);

    Class<T> getQueryClass();
}
