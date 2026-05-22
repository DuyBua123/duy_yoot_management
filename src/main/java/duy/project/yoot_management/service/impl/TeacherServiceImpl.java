package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.dto.teacher.TeacherUpsertRequest;
import duy.project.yoot_management.repository.TeacherRepository;
import duy.project.yoot_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher create(TeacherUpsertRequest teacher) {

        Teacher creatingTeacher = new Teacher();
        creatingTeacher.setTeacherCode(teacher.getTeacherCode());
        creatingTeacher.setFullName(teacher.getFullName());
        creatingTeacher.setEmail(teacher.getEmail());
        creatingTeacher.setPhone(teacher.getPhone());
        creatingTeacher.setTeacherRole(teacher.getTeacherRole());
        creatingTeacher.setCccdImageUrl(teacher.getCccdImageUrl());
        creatingTeacher.setActive(teacher.isActive());

        return teacherRepository.save(creatingTeacher);
    }

    @Override
    public Teacher update(Long id, TeacherUpsertRequest teacher) {

        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if (optionalTeacher.isEmpty()) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }

        Teacher updatingTeacher = optionalTeacher.get();
        updatingTeacher.setTeacherCode(teacher.getTeacherCode());
        updatingTeacher.setFullName(teacher.getFullName());
        updatingTeacher.setEmail(teacher.getEmail());
        updatingTeacher.setPhone(teacher.getPhone());
        updatingTeacher.setTeacherRole(teacher.getTeacherRole());
        updatingTeacher.setCccdImageUrl(teacher.getCccdImageUrl());
        updatingTeacher.setActive(teacher.isActive());

        return teacherRepository.save(updatingTeacher);
    }

    @Override
    public void deleteById(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

}
