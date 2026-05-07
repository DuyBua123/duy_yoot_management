package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.dto.request.TeacherUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll();

    Optional<Teacher> findById(Long id);

    Teacher create(TeacherUpsertRequest teacher);

    Teacher update(Long id, TeacherUpsertRequest teacher);

    void deleteById(Long id);

}
