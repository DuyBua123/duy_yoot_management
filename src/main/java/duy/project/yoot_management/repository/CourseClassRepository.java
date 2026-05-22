package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.CourseClass;
import duy.project.yoot_management.domains.enums.ClassStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {

    List<CourseClass> findByStatus(ClassStatus status);

}
