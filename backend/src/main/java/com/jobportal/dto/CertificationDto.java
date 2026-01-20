package com.jobportal.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CertificationDto {
	private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
}
