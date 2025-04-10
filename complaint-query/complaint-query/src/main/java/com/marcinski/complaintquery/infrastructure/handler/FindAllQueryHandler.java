package com.marcinski.complaintquery.infrastructure.handler;

import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.domain.ComplaintRepository;
import com.marcinski.complaintquery.infrastructure.query.FindAllComplaintQuery;
import com.marcinski.complaintquery.infrastructure.query.ListComplaintQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
class FindAllQueryHandler implements QueryHandler<FindAllComplaintQuery, ListComplaintQueryResponse> {

    private final ComplaintRepository complaintRepository;

    @Override
    @Transactional(readOnly = true)
    public ListComplaintQueryResponse handle(FindAllComplaintQuery query) {
        Iterable<Complaint> all = complaintRepository.findAll();
        List<Complaint> collect = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        return new ListComplaintQueryResponse(collect);
    }

    @Override
    public Class<FindAllComplaintQuery> getQueryClass() {
        return FindAllComplaintQuery.class;
    }
}
