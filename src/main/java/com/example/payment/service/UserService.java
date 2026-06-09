package com.example.payment.service;

import com.example.payment.model.User;

public interface UserService {
    User getById(Long id);
    User getCurrentUser();
}
