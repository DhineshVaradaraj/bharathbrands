package com.bank.authentication.service;// UserServiceImpl.java
import com.bank.authentication.entity.User;
import com.bank.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticateUser(String username, String password) {
        // Call core banking system to authenticate user and check if the account is active
        return userRepository.authenticateUser(username, password);
    }

    @Override
    public void reduceBillAmount(Double billAmount, int id) {
        userRepository.reduceBillAmount(billAmount, id);
    }
}
