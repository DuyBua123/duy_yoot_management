package duy.project.yoot_management.service;

import duy.project.yoot_management.dto.parent.ParentDashboardResponse;

public interface ParentPortalService {

    ParentDashboardResponse getDashboard(String username);

}
