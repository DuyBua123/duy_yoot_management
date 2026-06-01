package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.Student;
import duy.project.yoot_management.dto.student.StudentResponse;
import duy.project.yoot_management.dto.student.StudentUpsertRequest;
import duy.project.yoot_management.repository.ParentRepository;
import duy.project.yoot_management.repository.StudentRepository;
import duy.project.yoot_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ModelMapper mapper;


    public List<StudentResponse> findByAll() {
        return studentRepository.findAll().stream()
                .map(this::mapStudentResponse)
                .toList();
    }

    public List<StudentResponse> searchByFullName(String keyword) {
        return studentRepository.searchByFullName(keyword).stream()
                .map(this::mapStudentResponse)
                .toList();
    }

    public Optional<StudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::mapStudentResponse);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> findByParentId(Long parentId) {
        return studentRepository.findByParentId(parentId).stream().map(this::mapStudentResponse).toList();
    }

    @Transactional(readOnly = true)
    public Student getStudentForParent(Long studentId, Long parentId) throws NotFoundException {
        Student student = getStudent(studentId);
        if (student.getParent() == null || !student.getParent().getId().equals(parentId)) {
            throw new org.springframework.security.access.AccessDeniedException("Student does not belong to current parent account");
        }
        return student;
    }

    public Student getStudent(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    public StudentResponse create(StudentUpsertRequest request) {
        Student student = mapper.map(request, Student.class);

        parentRepository.findById(request.getParentId())
                .ifPresent(p -> student.setParent(p));
        student.setCreatedAt(java.time.LocalDateTime.now());
        student.setUpdatedAt(java.time.LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);

        return mapStudentResponse(savedStudent);
    }

    public StudentResponse update(Long id, StudentUpsertRequest request) {
        Student student = mapper.map(request, Student.class);
        student.setId(id);
        parentRepository.findById(request.getParentId())
                .ifPresent(p -> student.setParent(p));
        student.setUpdatedAt(java.time.LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);

        return mapStudentResponse(savedStudent);
    }

    public void delete(Long id) throws NotFoundException {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Student not found with id: " + id);
        }
    }



    // PRIVATE METHODS

    private StudentResponse mapStudentResponse(Student student) {
        return mapper.map(student, StudentResponse.class);
    }


}
