package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.domains.User;
import duy.project.yoot_management.dto.request.LoginRequest;
import duy.project.yoot_management.dto.response.AuthResponse;
import duy.project.yoot_management.repository.UserRepository;
import duy.project.yoot_management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;



    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
    }

}
