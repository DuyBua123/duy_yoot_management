package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.domains.Course;
import duy.project.yoot_management.dto.request.CourseUpsertRequest;
import duy.project.yoot_management.repository.CourseRepository;
import duy.project.yoot_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course create(CourseUpsertRequest course) {

        Course newCourse = new Course();
        newCourse.setCourseCode(course.getCourseCode());
        newCourse.setName(course.getName());
        newCourse.setDescription(course.getDescription());
        newCourse.setTuitionFee(course.getTuitionFee());
        newCourse.setTotalSessions(course.getTotalSessions());
        newCourse.setActive(course.isActive());

        return courseRepository.save(newCourse);
    }

    @Override
    public Course update(Long id, CourseUpsertRequest course) {

        Optional<Course> updatingCourse = courseRepository.findById(id);
        if (updatingCourse.isEmpty()) {
            throw new RuntimeException("Course not found with id: " + id);
        }

        Course existingCourse = updatingCourse.get();
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setTuitionFee(course.getTuitionFee());
        existingCourse.setTotalSessions(course.getTotalSessions());
        existingCourse.setActive(course.isActive());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteById(Long id) {

        Optional<Course> updatingCourse = courseRepository.findById(id);

        if (updatingCourse.isEmpty()) {
            throw new RuntimeException("Course not found with id: " + id);
        }

        courseRepository.delete(updatingCourse.get());
    }

}
