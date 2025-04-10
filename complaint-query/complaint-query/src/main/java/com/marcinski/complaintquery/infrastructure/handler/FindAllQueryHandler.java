package com.marcinski.complaintquery.infrastructure.handler;

import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.domain.ComplaintRepository;
import com.marcinski.complaintquery.infrastructure.exception.InvalidSortPropertyException;
import com.marcinski.complaintquery.infrastructure.query.FindAllComplaintQuery;
import com.marcinski.complaintquery.infrastructure.query.ListComplaintQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TreeSet;

@Component
@RequiredArgsConstructor
class FindAllQueryHandler implements QueryHandler<FindAllComplaintQuery, ListComplaintQueryResponse> {

    private static final Set<String> ALLOWED_SORT_PROPERTIES = new TreeSet<>(Set.of("creationDate", "reportCounter"));
    private final ComplaintRepository complaintRepository;

    @Override
    @Transactional(readOnly = true)
    public ListComplaintQueryResponse handle(FindAllComplaintQuery query) {
        Pageable pageable = query.getPageable();
        checkSortProperty(pageable);
        Page<Complaint> pagedComplaints = complaintRepository.findAll(pageable);
        return new ListComplaintQueryResponse(pagedComplaints);
    }

    private static void checkSortProperty(Pageable pageable) {
        for (Sort.Order order : pageable.getSort()) {
            if (!ALLOWED_SORT_PROPERTIES.contains(order.getProperty())) {
                String message = String.format("Invalid sort property: %s. Allowed property: %s",
                        order.getProperty(), ALLOWED_SORT_PROPERTIES);
                throw new InvalidSortPropertyException(message);
            }
        }
    }

    @Override
    public Class<FindAllComplaintQuery> getQueryClass() {
        return FindAllComplaintQuery.class;
    }
}
