package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.LearningResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResultRepository extends JpaRepository<LearningResult, Long> {

    boolean existsByStudentIdAndCourseClassIdAndResultMonth(Long studentId, Long courseClassId,
                                                            java.time.LocalDate resultMonth);

    java.util.List<LearningResult> findByStudentId(Long studentId);
}
