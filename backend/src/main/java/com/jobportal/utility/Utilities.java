package com.jobportal.utility;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.exception.JobPortalException;

@Component
public class Utilities {

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        Utilities.jdbcTemplate = jdbcTemplate;
    }

    /**
     * MySQL-based sequence generator
     */
    @Transactional
    public static Long getNextSequenceId(String key) throws JobPortalException {

        int updatedRows = jdbcTemplate.update(
                "UPDATE sequence SET seq = seq + 1 WHERE seq_key = ?",
                key
        );

        if (updatedRows == 0) {
            throw new JobPortalException("Unable to get sequence id for key : " + key);
        }

        Long nextSeq = jdbcTemplate.queryForObject(
                "SELECT seq FROM sequence WHERE seq_key = ?",
                Long.class,
                key
        );

        return nextSeq;
    }

    /**
     * OTP generation remains unchanged
     */
    public static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString();
    }
}
