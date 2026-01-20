package com.jobportal.dto;

import java.time.LocalDateTime;
import com.jobportal.entity.enums.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
	private Long id;
	private Long applicantId;
	private LocalDateTime interviewTime;
	private ApplicationStatus applicationStatus;
}
