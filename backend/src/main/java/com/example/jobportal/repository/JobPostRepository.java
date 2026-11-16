package com.example.jobportal.repository;

import com.example.jobportal.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    List<JobPost> findAllByCompanyId(Long companyId);

    @Query(value = """
            SELECT j.* FROM job_posts j
            JOIN companies c ON j.company_id = c.id
            WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.company_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(CAST(j.required_skills AS text)) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """, nativeQuery = true)
    List<JobPost> searchByKeyword(@Param("keyword") String keyword);
}