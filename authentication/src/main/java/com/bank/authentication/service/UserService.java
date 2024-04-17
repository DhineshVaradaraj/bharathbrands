package com.bank.authentication.service;

import com.bank.authentication.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User authenticateUser(String username, String password);

    void reduceBillAmount(Double billAmount, int id);
}
