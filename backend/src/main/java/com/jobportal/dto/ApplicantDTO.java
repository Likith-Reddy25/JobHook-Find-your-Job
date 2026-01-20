package com.jobportal.dto;


import java.time.LocalDateTime;
import java.util.Base64;

import com.jobportal.entity.Applicant;

import com.jobportal.entity.Job;
import com.jobportal.entity.enums.ApplicationStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {

    private Long jobId;
    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timestamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public Applicant toEntity(Job job) {
        Applicant applicant = new Applicant(
                this.applicantId,
                this.name,
                this.email,
                this.phone,
                this.website,
                this.resume != null ? Base64.getDecoder().decode(this.resume) : null,
                this.coverLetter,
                this.timestamp,
                this.applicationStatus,
                this.interviewTime,
                job
        );
        return applicant;
    }

}


