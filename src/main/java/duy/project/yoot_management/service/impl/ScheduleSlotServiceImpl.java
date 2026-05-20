package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.ScheduleSlot;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotResponse;
import duy.project.yoot_management.dto.schedule_slot.ScheduleSlotUpsertRequest;
import duy.project.yoot_management.repository.ScheduleSlotRepository;
import duy.project.yoot_management.service.ScheduleSlotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleSlotServiceImpl implements ScheduleSlotService {

    private final ScheduleSlotRepository scheduleSlotRepository;
    private final ModelMapper mapper;



    @Override
    public List<ScheduleSlotResponse> findAll() {
        return scheduleSlotRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Optional<ScheduleSlotResponse> findById(Long id) {
        return scheduleSlotRepository.findById(id)
                .map(this::mapToResponse);
    }

    @Override
    public ScheduleSlotResponse save(ScheduleSlotUpsertRequest request) {
        ScheduleSlot entity = mapper.map(request, ScheduleSlot.class);
        return mapToResponse(scheduleSlotRepository.save(entity));
    }

    @Override
    public ScheduleSlotResponse update(Long id, ScheduleSlotUpsertRequest request) {
        if (!scheduleSlotRepository.existsById(id)) {
            throw new NotFoundException("ScheduleSlot not found with id: " + id);
        }
        ScheduleSlot entity = mapper.map(request, ScheduleSlot.class);
        entity.setId(id);
        return mapToResponse(scheduleSlotRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!scheduleSlotRepository.existsById(id)) {
            throw new NotFoundException("ScheduleSlot not found with id: " + id);
        }
        scheduleSlotRepository.deleteById(id);
    }



    // PRIVATE METHOD
    private ScheduleSlotResponse mapToResponse(ScheduleSlot slot) {
        return mapper.map(slot, ScheduleSlotResponse.class);
    }

}

