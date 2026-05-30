package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.billing.InvoiceCreateRequest;
import duy.project.yoot_management.dto.billing.InvoiceResponse;

import java.util.List;

public interface BillingService {

    InvoiceResponse createInvoice(InvoiceCreateRequest request);

    List<InvoiceResponse> findInvoicesByStudent(Long studentId, String username);

}
