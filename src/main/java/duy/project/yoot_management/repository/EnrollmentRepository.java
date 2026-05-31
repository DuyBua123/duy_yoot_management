package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Enrollment;
import duy.project.yoot_management.domains.enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndCourseClassId(Long studentId, Long courseClassId);

    long countByCourseClassIdAndStatus(Long courseClassId, EnrollmentStatus status);

    List<Enrollment> findByCourseClassId(Long courseClassId);

    Optional<Enrollment> findByStudentIdAndCourseClassId(Long studentId, Long courseClassId);

    List<Enrollment> findByStudentId(Long studentId);

}
