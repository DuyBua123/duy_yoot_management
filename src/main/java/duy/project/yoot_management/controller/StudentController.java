package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.request.StudentUpsertRequest;
import duy.project.yoot_management.dto.response.StudentResponse;
import duy.project.yoot_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;



    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.findByAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid StudentUpsertRequest studentRequest) {
        return ResponseEntity.ok(studentService.create(studentRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @RequestBody @Valid StudentUpsertRequest studentRequest
    ) {
        return ResponseEntity.ok(studentService.update(id, studentRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
