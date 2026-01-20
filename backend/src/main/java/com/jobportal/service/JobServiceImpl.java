package com.jobportal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.Application;
import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;
import com.jobportal.dto.NotificationDTO;
import com.jobportal.entity.Applicant;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.entity.enums.ApplicationStatus;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.utility.Utilities;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;   // ✅ REQUIRED for Option 1

    @Autowired
    private NotificationService notificationService;

    // ---------------- POST / UPDATE JOB ----------------

    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {

//        if (jobDTO.getPostedById() == null) {
//            throw new JobPortalException("POSTED_BY_ID_REQUIRED");
//        }
        System.out.println("postedById from request = " + jobDTO.getPostedById());
        User user = userRepository.findById(jobDTO.getPostedById())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        if (jobDTO.getId() == null || jobDTO.getId() == 0) {

            jobDTO.setPostTime(LocalDateTime.now());

            Job savedJob = jobRepository.save(jobDTO.toEntity(user));

            NotificationDTO notiDto = new NotificationDTO();
            notiDto.setAction("Job Posted");
            notiDto.setMessage(
                    "Job Posted Successfully for " +
                            savedJob.getJobTitle() + " at " + savedJob.getCompany()
            );
            notiDto.setUserId(savedJob.getPostedBy().getId());
            notiDto.setRoute("/posted-jobs/" + savedJob.getId());

            notificationService.sendNotification(notiDto);

            return savedJob.toDTO();
        }

        Job job = jobRepository.findById(jobDTO.getId())
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        if (job.getJobStatus().equals(JobStatus.DRAFT)
                || jobDTO.getJobStatus().equals(JobStatus.CLOSED)) {
            jobDTO.setPostTime(LocalDateTime.now());
        }

        Job updatedJob = jobRepository.save(jobDTO.toEntity(user));


        return updatedJob.toDTO();
    }

    // ---------------- GET JOBS ----------------

    @Override
    public List<JobDTO> getAllJobs() throws JobPortalException {
        return jobRepository.findAll()
                .stream()
                .map(Job::toDTO)
                .toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalException {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"))
                .toDTO();
    }

    // ---------------- APPLY JOB ----------------

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        List<Applicant> applicants = job.getApplicants();
        if (applicants == null) {
            applicants = new ArrayList<>();
        }

        boolean alreadyApplied = applicants.stream()
                .anyMatch(a -> a.getApplicantId().equals(applicantDTO.getApplicantId()));

        if (alreadyApplied) {
            throw new JobPortalException("JOB_APPLIED_ALREADY");
        }

        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantDTO.toEntity(job));

        job.setApplicants(applicants);
        jobRepository.save(job);
    }

    // ---------------- JOB HISTORY ----------------

    @Override
    public List<JobDTO> getHistory(Long id, ApplicationStatus applicationStatus) {
        return jobRepository
                .findByApplicantIdAndApplicationStatus(id, applicationStatus)
                .stream()
                .map(Job::toDTO)
                .toList();
    }

    // ---------------- JOBS POSTED BY USER (OPTION 1 FIX) ----------------

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));

        return jobRepository.findByPostedBy(user)
                .stream()
                .map(Job::toDTO)
                .toList();
    }

    // ---------------- CHANGE APPLICATION STATUS ----------------

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {

        Job job = jobRepository.findById(application.getId())
                .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

        List<Applicant> updatedApplicants = job.getApplicants()
                .stream()
                .map(applicant -> {

                    if (applicant.getApplicantId()
                            .equals(application.getApplicantId())) {

                        applicant.setApplicationStatus(
                                application.getApplicationStatus()
                        );

                        if (application.getApplicationStatus()
                                .equals(ApplicationStatus.INTERVIEWING)) {

                            applicant.setInterviewTime(
                                    application.getInterviewTime()
                            );

                            NotificationDTO notiDto = new NotificationDTO();
                            notiDto.setAction("Interview Scheduled");
                            notiDto.setMessage(
                                    "Interview scheduled for job id: " +
                                            application.getId()
                            );
                            notiDto.setUserId(application.getApplicantId());
                            notiDto.setRoute("/job-history");

                            try {
                                notificationService.sendNotification(notiDto);
                            } catch (JobPortalException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return applicant;
                })
                .toList();

        job.setApplicants(updatedApplicants);
        jobRepository.save(job);
    }
}
