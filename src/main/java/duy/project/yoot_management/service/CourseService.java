package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Course;
import duy.project.yoot_management.dto.request.CourseRequest;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course create(CourseRequest course);

    Course update(Long id, CourseRequest course);

    void deleteById(Long id);

}
