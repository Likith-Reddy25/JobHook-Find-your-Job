package com.jobportal.entity;

import com.jobportal.dto.CertificationDto;
import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

import com.jobportal.dto.AccountType;
import com.jobportal.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ElementCollection
    @CollectionTable
    private List<CertificationDto> certifications= new ArrayList<>();

	private String name;
	private String email;
	private String password;
	private AccountType accountType;
	private Long profileId;

	public UserDTO toDTO() {
		return new UserDTO(this.id,this.name, this.email, this.password, this.accountType, this.profileId, this.certifications);
	}

}
