package duy.project.yoot_management.controller;

import duy.project.yoot_management.common.ApiResponse;
import duy.project.yoot_management.dto.auth.*;
import duy.project.yoot_management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successful", authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ApiResponse.success("Token refreshed", authService.refresh(request));
    }

    @PostMapping("/change-password")
    public ApiResponse<Void> changePassword(Principal principal,
                                            @Valid @RequestBody ChangePasswordRequest request) {
        authService.changePassword(principal.getName(), request);
        return ApiResponse.successMessage("Password changed successfully");
    }

    @GetMapping("/me")
    public ApiResponse<CurrentUserResponse> me(Principal principal) {
        return ApiResponse.success(authService.me(principal.getName()));
    }
}
