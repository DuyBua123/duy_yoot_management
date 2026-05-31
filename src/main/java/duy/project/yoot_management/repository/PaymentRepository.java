package duy.project.yoot_management.repository;

import duy.project.yoot_management.domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByInvoiceId(Long invoiceId);

    @Query("SELECT o FROM Payment o WHERE o.invoice.id = :invoiceId")
    List<Payment> findByInvoice(@Param("invoiceId") Long invoiceId);

}
