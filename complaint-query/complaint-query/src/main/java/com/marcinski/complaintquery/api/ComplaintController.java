package com.marcinski.complaintquery.api;

import com.marcinski.complaintquery.api.dto.ComplaintResponse;
import com.marcinski.complaintquery.api.dto.ListComplaintResponse;
import com.marcinski.complaintquery.facade.ComplaintQueryFacade;
import com.marcinski.complaintquery.infrastructure.query.FindAllComplaintQuery;
import com.marcinski.complaintquery.infrastructure.query.FindComplaintByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintQueryFacade complaintQueryFacade;

    @GetMapping
    public ResponseEntity<ListComplaintResponse> getComplaintById(Pageable pageable) {
        var query = new FindAllComplaintQuery(pageable);
        var response = complaintQueryFacade.getAllComplaints(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable String id) {
        var query = new FindComplaintByIdQuery(id);
        var response = complaintQueryFacade.getComplaintById(query);
        return ResponseEntity.ok(response);
    }
}
