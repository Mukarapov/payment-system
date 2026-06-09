package com.example.payment.service.impl;

import lombok.RequiredArgsConstructor;
import com.example.payment.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.payment.repository.UserRepository;
import com.example.payment.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found: " + id
                        )
                );
    }

    @Override
    public User getCurrentUser() {
        Long userId =
                (Long) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return getById(userId);
    }
}
