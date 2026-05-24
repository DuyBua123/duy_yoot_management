package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsByCourseClassIdAndStudentIdAndAttendanceDate(Long courseClassId, Long studentId, java.time.LocalDate attendanceDate);

    List<Attendance> findByCourseClassId(Long courseClassId);

    List<Attendance> findByStudentId(Long studentId);
}
