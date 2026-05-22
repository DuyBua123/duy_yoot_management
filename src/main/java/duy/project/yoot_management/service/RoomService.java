package duy.project.yoot_management.service;


import duy.project.yoot_management.dto.room.RoomResponse;
import duy.project.yoot_management.dto.room.RoomUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<RoomResponse> findAll();

    Optional<RoomResponse> findById(Long id);

    RoomResponse save(RoomUpsertRequest room);

    RoomResponse update(Long id, RoomUpsertRequest room);

    void deleteById(Long id);

}
