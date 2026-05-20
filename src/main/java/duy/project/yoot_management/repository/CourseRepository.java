package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT o FROM Course o WHERE o.isActive=1")
    List<Course> findByCourseActive();
}
