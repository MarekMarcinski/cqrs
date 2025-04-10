package com.marcinski.complaintquery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "complaints", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"complaintProductId", "reporterName"}, name = "uk_complaint_product_reporter")
})
@Data // Includes @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    private UUID id;
    private UUID complaintProductId;
    private String reporterName;
    private String contents;
    private LocalDate creationDate;
    private String country;
    private Integer reportCounter;

    public void incrementCounter() {
        if (this.reportCounter == null) {
            this.reportCounter = 1;
        }
        this.reportCounter++;
    }
}
