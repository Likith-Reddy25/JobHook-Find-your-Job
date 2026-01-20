package com.jobportal.entity;  // or wherever it is

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
}