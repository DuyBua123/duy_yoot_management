package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.learning_result.LearningResultCreateRequest;
import duy.project.yoot_management.dto.learning_result.LearningResultResponse;

import java.util.List;

public interface LearningResultService {

    LearningResultResponse create(LearningResultCreateRequest request, String username);
    List<LearningResultResponse> findByStudentId(Long studentId, String username);

}
