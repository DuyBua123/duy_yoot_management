package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotResponse;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotUpsertRequest;
import duy.project.yoot_management.service.ScheduleSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule-slots")
@RequiredArgsConstructor
public class ScheduleSlotController {

    private final ScheduleSlotService scheduleSlotService;



    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<List<ScheduleSlotResponse>> getAllScheduleSlots() {
        return ApiResponse.success(scheduleSlotService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ScheduleSlotResponse> getScheduleSlotById(@PathVariable Long id) {
        return scheduleSlotService.findById(id)
                .map(ApiResponse::success)
                .orElseThrow(() -> new NotFoundException("ScheduleSlot not found with id: " + id));
    }

    @PostMapping
    public ApiResponse<ScheduleSlotResponse> createScheduleSlot(
            @RequestBody @Valid ScheduleSlotUpsertRequest request
    ) {
        return ApiResponse.success(scheduleSlotService.save(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ScheduleSlotResponse> updateScheduleSlot(
            @PathVariable Long id,
            @RequestBody @Valid ScheduleSlotUpsertRequest request
    ) {
        return ApiResponse.success(scheduleSlotService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteScheduleSlot(@PathVariable Long id) {
        scheduleSlotService.deleteById(id);
        return ApiResponse.successMessage("ScheduleSlot deleted successfully");
    }

}
