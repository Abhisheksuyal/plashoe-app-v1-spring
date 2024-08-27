package com.abhishek.plashoeApp.PlashoeApp.controllers;

import com.abhishek.plashoeApp.PlashoeApp.dto.StringDTO;
import com.abhishek.plashoeApp.PlashoeApp.dto.WebhookPayloadDTO;
import com.abhishek.plashoeApp.PlashoeApp.services.WebhookProcessorService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.StringJoiner;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final WebhookProcessorService webhookProcessorService;

    @PostMapping("/webhook")
    public void handleWebhook(@RequestBody String payload) throws RazorpayException{
        try {
            log.info("webhook route hit");
             webhookProcessorService.extractPaymentData(payload);


        }catch(Exception e){
            throw new RazorpayException(e);
        }
    }


}
