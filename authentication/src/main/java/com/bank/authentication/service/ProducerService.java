package com.bank.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

   public void sendMessage(Integer userId) {

       jmsTemplate.convertAndSend("mobileapp", String.valueOf(userId));
    }
}

