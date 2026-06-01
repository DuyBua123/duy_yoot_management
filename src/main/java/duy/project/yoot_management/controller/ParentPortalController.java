package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.common.exception.BadRequestException;
import duy.project.yoot_management.common.exception.NotFoundException;
import duy.project.yoot_management.dto.parent.ParentDashboardResponse;
import duy.project.yoot_management.service.ParentPortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/parent")
@RequiredArgsConstructor
public class ParentPortalController {

    private final ParentPortalService parentPortalService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('PARENT')")
    public ApiResponse<ParentDashboardResponse> dashboard(Principal principal) throws BadRequestException, NotFoundException {
        return ApiResponse.success(parentPortalService.getDashboard(principal.getName()));
    }

}