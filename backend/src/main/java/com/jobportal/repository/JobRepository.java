package com.jobportal.repository;

import java.util.List;

import com.jobportal.entity.User;
import com.jobportal.entity.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("""
        SELECT j FROM Job j
        JOIN j.applicants a
        WHERE a.applicantId = :applicantId
        AND a.applicationStatus = :status
    """)
    List<Job> findByApplicantIdAndApplicationStatus(
            Long applicantId,
            ApplicationStatus status
    );

    List<Job> findByPostedBy(User postedBy);
}

