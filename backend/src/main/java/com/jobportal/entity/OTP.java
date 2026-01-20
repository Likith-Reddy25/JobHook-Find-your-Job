package com.jobportal.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

//import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "otp")
public class OTP {
	@Id
	private String email;


    private String otpCode;
    private LocalDateTime creationTime;
}
