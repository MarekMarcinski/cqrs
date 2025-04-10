package com.marcinski.complaintquery.api;

import com.marcinski.complaintquery.api.dto.ComplaintResponse;
import com.marcinski.complaintquery.api.dto.ListComplaintResponse;
import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.infrastructure.handler.QueryDispatcher;
import com.marcinski.complaintquery.infrastructure.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final QueryDispatcher<BaseQuery> queryDispatcher;

    @GetMapping
    public ResponseEntity<ListComplaintResponse> getComplaints() {
        var query = new FindAllComplaintQuery();
        ListComplaintQueryResponse response = (ListComplaintQueryResponse) queryDispatcher.send(query);
        List<ComplaintResponse> list = response.getComplaints().stream().map(complaint ->
                new ComplaintResponse(complaint.getId().toString(), complaint.getComplaintProductId().toString(),
                        complaint.getReporterName(), complaint.getContents(), complaint.getCountry(),
                        complaint.getReportCounter())).toList();
        return ResponseEntity.ok(new ListComplaintResponse(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaints(@PathVariable String id) {
        var query = new FindComplaintByIdQuery(id);
        SingleComplaintQueryResponse response = (SingleComplaintQueryResponse) queryDispatcher.send(query);
        Complaint complaint = response.getComplaint();
        ComplaintResponse complaintResponse = new ComplaintResponse(complaint.getId().toString(), complaint.getComplaintProductId().toString(),
                complaint.getReporterName(), complaint.getContents(), complaint.getCountry(),
                complaint.getReportCounter());

        return ResponseEntity.ok(complaintResponse);
    }
}
