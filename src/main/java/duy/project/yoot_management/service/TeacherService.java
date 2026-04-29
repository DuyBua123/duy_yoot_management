package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.dto.request.TeacherRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<Teacher> findAll();

    Optional<Teacher> findById(Long id);

    Teacher create(TeacherRequest teacher);

    Teacher update(Long id, TeacherRequest teacher);

    void deleteById(Long id);

}
