package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.Student;
import duy.project.yoot_management.dto.request.StudentUpsertRequest;
import duy.project.yoot_management.dto.response.ParentResponse;
import duy.project.yoot_management.dto.response.StudentResponse;
import duy.project.yoot_management.repository.ParentRepository;
import duy.project.yoot_management.repository.StudentRepository;
import duy.project.yoot_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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


    /*private StudentResponse map(Student student) {
        StudentResponse result = new StudentResponse();
        ParentResponse pResult = new ParentResponse();

        if (student.getParent() != null) {
            pResult.setId(student.getParent().getId());
            pResult.setFullName(student.getParent().getFullName());
            pResult.setPhone(student.getParent().getPhone());
            pResult.setEmail(student.getParent().getEmail());
            pResult.setAddress(student.getParent().getAddress());
            pResult.setRelationship(student.getParent().getRelationship());
            pResult.setGender(student.getParent().getGender());
        }

        result.setId(student.getId());
        result.setStudentCode(student.getStudentCode());
        result.setFullName(student.getFullName());
        result.setDateOfBirth(student.getDateOfBirth());
        result.setGender(student.getGender());
        result.setGradeLevel(student.getGradeLevel());
        result.setSchoolName(student.getSchoolName());
        result.setPhone(student.getPhone());
        result.setDescription(student.getDescription());
        result.setParent(pResult);
        result.setStatus(student.getStatus());
        result.setLatestScore(student.getLatestScore());
        result.setNote(student.getNote());

        return result;
    }*/


    private StudentResponse mapStudentResponse(Student student) {
        return mapper.map(student, StudentResponse.class);
    }

    public Optional<StudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::mapStudentResponse);
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

}
