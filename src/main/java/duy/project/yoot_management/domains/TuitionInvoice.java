package duy.project.yoot_management.domains;

import duy.project.yoot_management.domains.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tuition_invoices")
public class TuitionInvoice extends AuditableEntity {

    @Column(name = "invoice_code", nullable = false, unique = true, length = 30)
    private String invoiceCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_class_id", nullable = false)
    private CourseClass courseClass;

    @Column(name = "billing_month", nullable = false)
    private LocalDate billingMonth;

    @Column(name = "original_amount", nullable = false, precision = 12, scale = 2)
    private float originalAmount;

    @Column(name = "discount_amount", nullable = false, precision = 12, scale = 2)
    private float discountAmount;

    @Column(name = "final_amount", nullable = false, precision = 12, scale = 2)
    private float finalAmount;

    @Column(name = "amount_paid", nullable = false, precision = 12, scale = 2)
    private float amountPaid;

    @Column(name = "balance_amount", nullable = false, precision = 12, scale = 2)
    private float balanceAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InvoiceStatus status = InvoiceStatus.UNPAID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(length = 255)
    private String note;
}
