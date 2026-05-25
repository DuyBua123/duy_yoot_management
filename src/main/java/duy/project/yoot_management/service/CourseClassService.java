package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.CourseClass;
import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.course_class.CourseClassResponse;
import duy.project.yoot_management.dto.course_class.CourseClassUpsertRequest;

import java.util.List;

public interface CourseClassService {

    List<CourseClassResponse> findAll(ClassStatus status);
    CourseClass getCourseClass(Long id);
    CourseClassResponse create(CourseClassUpsertRequest request);

}

