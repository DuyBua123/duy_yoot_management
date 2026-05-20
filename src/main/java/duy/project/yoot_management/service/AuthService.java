package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.User;
import duy.project.yoot_management.dto.request.ChangePasswordRequest;
import duy.project.yoot_management.dto.request.LoginRequest;
import duy.project.yoot_management.dto.request.RefreshTokenRequest;
import duy.project.yoot_management.dto.response.AuthResponse;
import duy.project.yoot_management.dto.response.CurrentUserResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse refresh(RefreshTokenRequest request);

    void changePassword(String username, ChangePasswordRequest request);

    CurrentUserResponse me(String username);

    User findActiveUserByUsername(String username);

}
