package duy.project.yoot_management.service;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.request.StudentUpsertRequest;
import duy.project.yoot_management.dto.response.StudentResponse;

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
