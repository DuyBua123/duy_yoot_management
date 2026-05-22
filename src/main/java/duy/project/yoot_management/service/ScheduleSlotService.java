package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotResponse;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface ScheduleSlotService {

    List<ScheduleSlotResponse> findAll();

    Optional<ScheduleSlotResponse> findById(Long id);

    ScheduleSlotResponse save(ScheduleSlotUpsertRequest request);

    ScheduleSlotResponse update(Long id, ScheduleSlotUpsertRequest request);

    void deleteById(Long id);

}

