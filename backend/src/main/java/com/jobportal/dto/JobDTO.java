package com.jobportal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jobportal.entity.Job;
import com.jobportal.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {

    private Long id;
    private String jobTitle;
    private String company;
    private List<ApplicantDTO> applicants;
    private String about;
    private String experience;
    private String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedById;

    public Job toEntity(User user) {

        Job job = new Job(
                this.id,
                this.jobTitle,
                this.company,
                null,
                this.about,
                this.experience,
                this.jobType,
                this.location,
                this.packageOffered,
                this.postTime,
                this.description,
                this.skillsRequired,
                this.jobStatus,
                user // ✅ managed entity
        );

        if (this.applicants != null) {
            job.setApplicants(
                    this.applicants.stream()
                            .map(dto -> dto.toEntity(job))
                            .toList()
            );
        }

        return job;
    }
}
