package com.marcinski.complaintquery.infrastructure.handler;

import com.marcinski.complaintquery.infrastructure.query.BaseQuery;
import com.marcinski.complaintquery.infrastructure.query.QueryResponse;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class QueryDispatcher<T extends BaseQuery> {
    private final Set<QueryHandler<? extends BaseQuery, ? extends QueryResponse>> handlers;

    public QueryDispatcher(Set<QueryHandler<? extends BaseQuery, ? extends QueryResponse>> handlers) {
        this.handlers = handlers;
    }

    public QueryResponse send(BaseQuery query) {
        var commandHandlerMethod = handlers.stream()
                .filter(h -> h.getQueryClass()
                        .equals(query.getClass()))
                .map(QueryHandler.class::cast)
                .findFirst()
                .orElse(null); //throw
        return commandHandlerMethod.handle(query);
    }
}
