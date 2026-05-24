package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.atteance.AttendanceCreateRequest;
import duy.project.yoot_management.dto.atteance.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse create(AttendanceCreateRequest request, String username);

    List<AttendanceResponse> findByClassId(Long classId);

}
