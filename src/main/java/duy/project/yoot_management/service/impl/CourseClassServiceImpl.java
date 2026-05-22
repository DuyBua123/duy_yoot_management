package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.BadRequestException;
import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.Course;
import duy.project.yoot_management.domains.CourseClass;
import duy.project.yoot_management.domains.Room;
import duy.project.yoot_management.domains.ScheduleSlot;
import duy.project.yoot_management.domains.Teacher;
import duy.project.yoot_management.domains.enums.ClassStatus;
import duy.project.yoot_management.dto.course_class.CourseClassResponse;
import duy.project.yoot_management.dto.course_class.CourseClassUpsertRequest;
import duy.project.yoot_management.repository.CourseClassRepository;
import duy.project.yoot_management.repository.CourseRepository;
import duy.project.yoot_management.repository.RoomRepository;
import duy.project.yoot_management.repository.ScheduleSlotRepository;
import duy.project.yoot_management.repository.TeacherRepository;
import duy.project.yoot_management.service.CourseClassService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassServiceImpl implements CourseClassService {

    private final CourseClassRepository courseClassRepository;
    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    @Override
    public List<CourseClassResponse> findAll(ClassStatus status) {
        List<CourseClass> classes = (status != null)
                ? courseClassRepository.findByStatus(status)
                : courseClassRepository.findAll();

        return classes.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CourseClassResponse create(CourseClassUpsertRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + request.getCourseId()));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + request.getRoomId()));

        if (request.getMaxStudents() > room.getCapacity()) {
            throw new BadRequestException(
                    "maxStudents (" + request.getMaxStudents() + ") exceeds room capacity (" + room.getCapacity() + ")"
            );
        }

        ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(request.getScheduleSlotId())
                .orElseThrow(() -> new NotFoundException("ScheduleSlot not found with id: " + request.getScheduleSlotId()));

        Teacher mainTeacher = teacherRepository.findById(request.getMainTeacherId())
                .orElseThrow(() -> new NotFoundException("Teacher not found with id: " + request.getMainTeacherId()));

        Teacher assistantTeacher = null;
        if (request.getAssistantTeacherId() != null) {
            assistantTeacher = teacherRepository.findById(request.getAssistantTeacherId())
                    .orElseThrow(() -> new NotFoundException("Assistant teacher not found with id: " + request.getAssistantTeacherId()));
        }

        // Auto-generate classCode: {courseCode}-{roomName}-{year}
        int year = request.getStartDate().getYear();
        String classCode = course.getCourseCode() + "-" + room.getName() + "-" + year;

        CourseClass courseClass = new CourseClass();
        courseClass.setClassCode(classCode);
        courseClass.setName(request.getName());
        courseClass.setCourse(course);
        courseClass.setRoom(room);
        courseClass.setScheduleSlot(scheduleSlot);
        courseClass.setTeacher(mainTeacher);
        courseClass.setAssistantTeacher(assistantTeacher);
        courseClass.setStartDate(request.getStartDate());
        courseClass.setEndDate(request.getEndDate());
        courseClass.setMaxStudents(request.getMaxStudents());
        courseClass.setTuitionFee(request.getTuitionFee());
        courseClass.setStatus(request.getStatus());

        return mapToResponse(courseClassRepository.save(courseClass));
    }

    // PRIVATE METHOD
    private CourseClassResponse mapToResponse(CourseClass courseClass) {
        return mapper.map(courseClass, CourseClassResponse.class);
    }

}

