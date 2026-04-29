package duy.project.yoot_management.domains;

import duy.project.yoot_management.domains.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "promotions")
@Data
public class Promotion extends AuditableEntity {

    @Column(name = "promo_code", columnDefinition = "varchar(30)", unique = true, nullable = false)
    private String promoCode;
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String name;
    private String note;

    @Column(name = "discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(name = "discount_value", columnDefinition = "decimal(12,2)", nullable = false)
    private double discountValue;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

}
