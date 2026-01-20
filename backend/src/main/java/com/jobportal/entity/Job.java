package com.jobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobTitle;

    private String company;

    // One Job -> Many Applicants
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Applicant> applicants;

    private String about;
    private String experience;
    private String jobType;
    private String location;

    private Long packageOffered;

    private LocalDateTime postTime;

    @Column(length = 2000)
    private String description;

    // List of basic values
    @ElementCollection
    @CollectionTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id")
    )
    @Column(name = "skill")
    private List<String> skillsRequired;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    // Recommended: relationship instead of Long
    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;

    public JobDTO toDTO() {
        return new JobDTO(
                this.id,
                this.jobTitle,
                this.company,
                this.applicants != null
                        ? this.applicants.stream().map(Applicant::toDTO).toList()
                        : null,
                this.about,
                this.experience,
                this.jobType,
                this.location,
                this.packageOffered,
                this.postTime,
                this.description,
                this.skillsRequired,
                this.jobStatus,
                this.postedBy != null ? this.postedBy.getId() : null
        );
    }
}
