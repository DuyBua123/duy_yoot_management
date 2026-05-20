package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.ScheduleSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
}
