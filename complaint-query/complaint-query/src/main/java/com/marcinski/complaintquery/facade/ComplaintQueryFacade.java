package com.marcinski.complaintquery.facade;

import com.marcinski.complaintquery.api.dto.ComplaintResponse;
import com.marcinski.complaintquery.api.dto.ListComplaintResponse;
import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.infrastructure.handler.QueryDispatcher;
import com.marcinski.complaintquery.infrastructure.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ComplaintQueryFacade {

    private final QueryDispatcher<BaseQuery> queryDispatcher;
    private final ComplaintMapper mapper;


    public ListComplaintResponse getAllComplaints(FindAllComplaintQuery query) {
        ListComplaintQueryResponse response = (ListComplaintQueryResponse) queryDispatcher.send(query);
        List<ComplaintResponse> list = response.getComplaints()
                .stream()
                .map(mapper::map)
                .toList();
        return new ListComplaintResponse(list);
    }

    public ComplaintResponse getComplaintById(FindComplaintByIdQuery query) {
        SingleComplaintQueryResponse response = (SingleComplaintQueryResponse) queryDispatcher.send(query);
        Complaint complaint = response.getComplaint();
        return mapper.map(complaint);
    }
}
