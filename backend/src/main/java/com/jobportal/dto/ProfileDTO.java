package com.jobportal.dto;

import java.util.Base64;
import java.util.List;

import com.jobportal.entity.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
	private Long id;
	private String name;
	private String email;
	private String jobTitle;
	private String company;
	private String location;
	private String about;
	private String picture;
	private Long totalExp;
	private List<String> skills;
	private List<Experience>experiences;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private List<CertificationDto> certificationDtos;

	private List<Long>savedJobs;
	
	public Profile toEntity() {
		return new Profile(this.id,this.name, this.email, this.jobTitle, this.company, this.location, this.about,this.picture!=null?Base64.getDecoder().decode(this.picture):null,this.totalExp, this.skills, this.experiences, this.certificationDtos, this.savedJobs);
	}
}
