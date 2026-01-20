package com.jobportal.entity;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.*;

import com.jobportal.dto.CertificationDto;
import com.jobportal.dto.Experience;
import com.jobportal.dto.ProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;

    @Lob
    private byte[] picture;

    private Long totalExp;

    @ElementCollection
    @CollectionTable(name = "profile_skills", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "profile_experiences", joinColumns = @JoinColumn(name = "profile_id"))
    private List<Experience> experiences = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "profile_certifications", joinColumns = @JoinColumn(name = "profile_id"))
    private List<CertificationDto> certificationDtos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "profile_saved_jobs", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "job_id")
    private List<Long> savedJobs = new ArrayList<>();

    public ProfileDTO toDTO() {
        return new ProfileDTO(this.id, this.name, this.email, this.jobTitle, this.company,
                this.location, this.about,
                this.picture != null ? Base64.getEncoder().encodeToString(this.picture) : null,
                this.totalExp, this.skills, this.experiences, this.certificationDtos, this.savedJobs);
    }
}