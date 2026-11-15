package com.example.jobportal.repository;

import com.example.jobportal.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    boolean existsBySeekerIdAndJobPostId(Long seekerId, Long jobPostId);

    @Query("SELECT ja FROM JobApplication ja " +
            "JOIN FETCH ja.jobPost jp " +
            "JOIN FETCH jp.company " +
            "WHERE ja.seeker.id = :seekerId")
    List<JobApplication> findAllBySeekerId(@Param("seekerId") Long seekerId);

    List<JobApplication> findAllByJobPostId(Long jobPostId);
}
