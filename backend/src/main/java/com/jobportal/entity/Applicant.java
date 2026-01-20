package com.jobportal.entity;
import com.jobportal.entity.enums.ApplicationStatus;
import jakarta.persistence.*;



import java.time.LocalDateTime;
import java.util.Base64;

import com.jobportal.dto.ApplicantDTO;

//import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;

    private String name;
    private String email;
    private Long phone;
    private String website;

    @Lob
    private byte[] resume;

    private String coverLetter;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private LocalDateTime interviewTime;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    public ApplicantDTO toDTO() {
        return new ApplicantDTO(
                this.job != null ? this.job.getId() : null,
                this.applicantId,
                this.name,
                this.email,
                this.phone,
                this.website,
                this.resume != null ? Base64.getEncoder().encodeToString(this.resume) : null,
                this.coverLetter,
                this.timestamp,
                this.applicationStatus,
                this.interviewTime
        );
    }
}

