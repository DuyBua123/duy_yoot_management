package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Enrollment;
import duy.project.yoot_management.dto.enrollment.EnrollmentCreateRequest;
import duy.project.yoot_management.dto.enrollment.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse create(EnrollmentCreateRequest request);

    List<EnrollmentResponse> findByClassId(Long classId);

    Enrollment getEnrollment(Long studentId, Long classId);

}
