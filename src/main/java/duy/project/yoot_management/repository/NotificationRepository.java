package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Notification;
import duy.project.yoot_management.domains.enums.NotificationRecipientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByRecipientTypeAndRecipientRefIdOrderByCreatedAtDesc(NotificationRecipientType recipientType, Long recipientRefId);

}
