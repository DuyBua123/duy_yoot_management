package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Room;
import duy.project.yoot_management.dto.request.RoomUpsertRequest;
import duy.project.yoot_management.dto.response.RoomResponse;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<RoomResponse> findAll();

    Optional<RoomResponse> findById(Long id);

    RoomResponse save(RoomUpsertRequest room);

    RoomResponse update(Long id, RoomUpsertRequest room);

    void deleteById(Long id);

}
