package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.fullName LIKE %:keyword%")
    List<Student> searchByFullName(String keyword);

    List<Student> findByParentId(Long parentId);

}
