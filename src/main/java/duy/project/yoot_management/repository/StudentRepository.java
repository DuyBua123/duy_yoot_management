package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
