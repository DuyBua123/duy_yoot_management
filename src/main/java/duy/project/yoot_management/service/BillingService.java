package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.billing.InvoiceCreateRequest;
import duy.project.yoot_management.dto.billing.InvoiceResponse;
import duy.project.yoot_management.dto.billing.PaymentCreateRequest;
import duy.project.yoot_management.dto.billing.PaymentResponse;

import java.util.List;

public interface BillingService {

    InvoiceResponse createInvoice(InvoiceCreateRequest request);

    List<InvoiceResponse> findInvoicesByStudent(Long studentId, String username);

    PaymentResponse createPayment(PaymentCreateRequest request, String username);

}
