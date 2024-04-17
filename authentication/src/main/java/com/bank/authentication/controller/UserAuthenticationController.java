package com.bank.authentication.controller;// UserAuthenticationController.java
import com.bank.authentication.exception.InsufficientBalanceException;
import com.bank.authentication.entity.User;
import com.bank.authentication.service.ProducerService;
import com.bank.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProducerService producerService;

    private User validUser;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if(validUser==null) {
            validUser = userService.authenticateUser(username, password);
            if (validUser != null) {

                return ResponseEntity.ok("Logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Need to logout in case of a new user to access");
        }
    }

    @PostMapping("/login/payment")
    public ResponseEntity<String> processPayment(Double billAmount) {
        try {
            if (validUser!= null && validUser.getAccBalance() < billAmount) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }
            producerService.sendMessage(validUser.getId());
            userService.reduceBillAmount(billAmount, validUser.getId());
            return ResponseEntity.status(HttpStatus.CONTINUE).body("Payment processed");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        validUser = null;
        return ResponseEntity.ok("LoggedOut successfully");
    }




}
