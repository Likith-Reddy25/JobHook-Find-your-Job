package com.jobportal.entity;
import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Table(name="sequence")
@Data
public class Sequence {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private Long seq;
}
