package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.Room;
import duy.project.yoot_management.dto.request.RoomUpsertRequest;
import duy.project.yoot_management.dto.response.RoomResponse;
import duy.project.yoot_management.repository.RoomRepository;
import duy.project.yoot_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper mapper;



    public List<RoomResponse> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Optional<RoomResponse> findById(Long id) {
        return roomRepository.findById(id)
                .map(this::mapToResponse);
    }

    public RoomResponse save(RoomUpsertRequest room) {
        Room roomEntity = mapper.map(room, Room.class);
        return mapToResponse(
                roomRepository.save(roomEntity)
        );
    }

    public RoomResponse update(Long id, RoomUpsertRequest room) {
        Room updatingRoom = mapper.map(room, Room.class);
        updatingRoom.setId(id);

        return mapToResponse(
                roomRepository.save(updatingRoom)
        );
    }

    public void deleteById(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }



    // PRIVATE METHOD
    private RoomResponse mapToResponse(Room room) {
        return mapper.map(room, RoomResponse.class);
    }

}
