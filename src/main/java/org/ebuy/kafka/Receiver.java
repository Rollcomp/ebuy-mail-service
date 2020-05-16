package org.ebuy.kafka;

import org.ebuy.model.TokenMail;
import org.ebuy.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Ozgur Ustun on May, 2020
 */
@Service
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private final EmailService emailService;

    @Autowired
    public Receiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.userRegistered}", autoStartup = "true")
    public void receiveRegisterMail(@Payload TokenMail receiveMailDto) {
        LOGGER.debug("received paylod" + receiveMailDto.toString());
        emailService.sendRegisterMail(receiveMailDto);
        latch.countDown();
    }

    @KafkaListener(topics = "${spring.kafka.topic.userPassword}", autoStartup = "true")
    public void receivePasswordResetMail(@Payload TokenMail mailDto) {
        LOGGER.debug("received paylod" + mailDto.toString());
        emailService.sendPasswordResetMail(mailDto);
        latch.countDown();
    }


}
