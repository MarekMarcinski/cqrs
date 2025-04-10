package com.marcinski.complaintquery.facade;

import com.marcinski.complaintquery.api.dto.ComplaintResponse;
import com.marcinski.complaintquery.domain.Complaint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ComplaintMapper {
    ComplaintResponse map(Complaint complaint);
}
