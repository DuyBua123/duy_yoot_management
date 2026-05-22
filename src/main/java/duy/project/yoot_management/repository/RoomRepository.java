package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByNameLike(String name);
}
