package com.marcinski.complaintquery.infrastructure.handler;

import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.domain.ComplaintRepository;
import com.marcinski.complaintquery.infrastructure.query.FindComplaintByIdQuery;
import com.marcinski.complaintquery.infrastructure.query.SingleComplaintQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class FindSingleQueryHandler implements QueryHandler<FindComplaintByIdQuery, SingleComplaintQueryResponse> {

    private final ComplaintRepository complaintRepository;

    @Override
    @Transactional(readOnly = true)
    public SingleComplaintQueryResponse handle(FindComplaintByIdQuery query) {
        Complaint complaint = complaintRepository.findById(UUID.fromString(query.getId())).orElseThrow();
        return new SingleComplaintQueryResponse(complaint);
    }

    @Override
    public Class<FindComplaintByIdQuery> getQueryClass() {
        return FindComplaintByIdQuery.class;
    }
}
