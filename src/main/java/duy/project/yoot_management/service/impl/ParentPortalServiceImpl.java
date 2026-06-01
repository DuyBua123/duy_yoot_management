package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.common.exception.BadRequestException;
import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.domains.User;
import duy.project.yoot_management.domains.enums.NotificationRecipientType;
import duy.project.yoot_management.domains.enums.UserRole;
import duy.project.yoot_management.dto.parent.InvoiceCard;
import duy.project.yoot_management.dto.parent.NotificationCard;
import duy.project.yoot_management.dto.parent.ParentDashboardResponse;
import duy.project.yoot_management.dto.parent.StudentCard;
import duy.project.yoot_management.repository.NotificationRepository;
import duy.project.yoot_management.repository.TuitionInvoiceRepository;
import duy.project.yoot_management.service.AuthService;
import duy.project.yoot_management.service.ParentPortalService;
import duy.project.yoot_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentPortalServiceImpl implements ParentPortalService {
    private final AuthService authService;
    private final StudentService studentService;
    private final TuitionInvoiceRepository tuitionInvoiceRepository;
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public ParentDashboardResponse getDashboard(String username) throws BadRequestException, NotFoundException {
        User user = authService.findActiveUserByUsername(username);
        if (user.getRole() != UserRole.PARENT || user.getParent() == null) {
            throw new BadRequestException("Current user is not a parent account");
        }

        Long parentId = user.getParent().getId();
        List<StudentCard> students = studentService.findByParentId(parentId).stream()
                .map(s -> new StudentCard(
                        s.getId(), s.getStudentCode(), s.getFullName(), s.getStatus().toString(), s.getLatestScore()))
                .toList();

        List<InvoiceCard> invoices = tuitionInvoiceRepository.findByStudentParentId(parentId).stream()
                .map(i -> new InvoiceCard(
                        i.getId(),
                        i.getInvoiceCode(),
                        i.getStudent().getFullName(),
                        i.getCourseClass().getName(),
                        i.getBillingMonth(),
                        i.getFinalAmount(),
                        i.getAmountPaid(),
                        i.getBalanceAmount(),
                        i.getStatus().name(),
                        i.getDueDate()
                ))
                .toList();

        List<NotificationCard> notifications = notificationRepository
                .findByRecipientTypeAndRecipientRefIdOrderByCreatedAtDesc(NotificationRecipientType.PARENT, parentId)
                .stream()
                .map(n -> new NotificationCard(
                        n.getId(),
                        n.getType().name(),
                        n.getTitle(),
                        n.getContent(),
                        n.getIsRead(),
                        n.getCreatedAt()
                ))
                .toList();

        return new ParentDashboardResponse(
                parentId,
                user.getParent().getFullName(),
                user.getUsername(),
                students,
                invoices,
                notifications
        );
    }
}