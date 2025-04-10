package com.marcinski.complaintquery.domain;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComplaintRepository extends CrudRepository<Complaint, UUID> {
}
