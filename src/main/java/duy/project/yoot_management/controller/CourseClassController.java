package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.course_class.CourseClassResponse;
import duy.project.yoot_management.dto.course_class.CourseClassUpsertRequest;
import duy.project.yoot_management.service.CourseClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-classes")
@RequiredArgsConstructor
public class CourseClassController {

    private final CourseClassService courseClassService;

    @GetMapping
    public ApiResponse<List<CourseClassResponse>> getAllCourseClasses(
            @RequestParam(required = false) ClassStatus status
    ) {
        return ApiResponse.success(courseClassService.findAll(status));
    }

    @PostMapping
    public ApiResponse<CourseClassResponse> createCourseClass(
            @RequestBody @Valid CourseClassUpsertRequest request
    ) {
        return ApiResponse.success(courseClassService.create(request));
    }

}

