package com.jobportal.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


import com.jobportal.dto.NotificationDTO;
import com.jobportal.dto.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String message;
	private String action;
	private String route;
	private NotificationStatus status;
	private LocalDateTime timestamp;
	public NotificationDTO toDTO() {
		return new NotificationDTO (this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
	}
}
