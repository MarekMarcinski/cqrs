package com.marcinski.complaintquery.infrastructure;

import com.marcinski.complaintquery.domain.Complaint;
import com.marcinski.complaintquery.domain.ComplaintRepository;
import com.marcinski.complaintquery.geo.CityLocator;
import com.marcinski.complaintquery.infrastructure.event.ComplaintContentsChangedEvent;
import com.marcinski.complaintquery.infrastructure.event.ComplaintCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ComplaintEventHandler {

    private final ComplaintRepository complaintRepository;
    private final ComplaintCommandMapper complaintCommandMapper;
    private final CityLocator cityLocator;

    void on(ComplaintCreatedEvent event) {
        UUID id = UUID.fromString(event.getId());
        Optional<Complaint> byId = complaintRepository.findById(id);
        Complaint complaint;
        if (byId.isPresent()) {
            complaint = byId.get();
            complaint.incrementCounter();
        } else {
            String country = cityLocator.getCityByIp(event.getIpAddress());
            complaint = complaintCommandMapper.map(event, country);
        }
        complaintRepository.save(complaint);
    }

    void on(ComplaintContentsChangedEvent event) {
        UUID id = UUID.fromString(event.getId());
        Optional<Complaint> byId = complaintRepository.findById(id);
        if (byId.isPresent()) {
            Complaint complaint = byId.get();
            complaint.setContents(event.getContents());
            complaintRepository.save(complaint);
        }
    }
}
