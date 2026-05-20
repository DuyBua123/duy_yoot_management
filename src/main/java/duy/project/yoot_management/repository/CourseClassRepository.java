package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {
}
