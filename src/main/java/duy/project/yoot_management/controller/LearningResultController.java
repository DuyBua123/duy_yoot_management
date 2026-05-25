package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.dto.learning_result.LearningResultCreateRequest;
import duy.project.yoot_management.dto.learning_result.LearningResultResponse;
import duy.project.yoot_management.service.LearningResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/learning-results")
@RequiredArgsConstructor
public class LearningResultController {

    private final LearningResultService learningResultService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<LearningResultResponse> create(@Valid @RequestBody LearningResultCreateRequest request, Principal principal) {
        return ApiResponse.success("Learning result created", learningResultService.create(request, principal.getName()));
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF','PARENT')")
    public ApiResponse<List<LearningResultResponse>> findByStudentId(@PathVariable Long studentId, Principal principal) {
        return ApiResponse.success(learningResultService.findByStudentId(studentId, principal.getName()));
    }

}
