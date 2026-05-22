package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.room.RoomResponse;
import duy.project.yoot_management.dto.room.RoomUpsertRequest;
import duy.project.yoot_management.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;



    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return ApiResponse.success(roomService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable Long id) {
        return roomService.findById(id)
                .map(ApiResponse::success)
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
    }

    @PostMapping
    public ApiResponse<RoomResponse> createRoom(@RequestBody @Valid RoomUpsertRequest room) {
        return ApiResponse.success(roomService.save(room));
    }

    @PutMapping("/{id}")
    public ApiResponse<RoomResponse> updateRoom(
            @PathVariable Long id,
            @RequestBody @Valid RoomUpsertRequest room
    ) {
        return ApiResponse.success(roomService.update(id, room));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
        return ApiResponse.successMessage("Room deleted successfully");
    }

}
