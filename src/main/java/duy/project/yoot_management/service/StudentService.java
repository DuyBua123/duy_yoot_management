package duy.project.yoot_management.service;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.student.StudentResponse;
import duy.project.yoot_management.dto.student.StudentUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponse> findByAll();
    List<StudentResponse> searchByFullName(String keyword);
    Optional<StudentResponse> findById(Long id);
    StudentResponse create(StudentUpsertRequest studentResponse);
    StudentResponse update(Long id, StudentUpsertRequest studentResponse);
    void delete(Long id) throws NotFoundException;
}
