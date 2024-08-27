package com.abhishek.plashoeApp.PlashoeApp.services;

import com.abhishek.plashoeApp.PlashoeApp.entities.OrderEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.repositories.OrderRepository;
import com.abhishek.plashoeApp.PlashoeApp.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class WebhookProcessorService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(WebhookProcessorService.class);

    public void extractPaymentData(String payload) throws Exception {
        log.info("inside payment data webhook method ");
        JsonNode rootNode = objectMapper.readTree(payload);
        JsonNode paymentNode = rootNode.path("payload").path("payment").path("entity");

        String id = paymentNode.path("id").asText();
        Long amount = paymentNode.path("amount").asLong();
        String status = paymentNode.path("status").asText();
        log.info(id);
        log.info(amount+"");
        log.info(status);
        String orderId = paymentNode.path("order_id").asText();

        OrderEntity orderEntity = orderRepository.findByRazorpayId(orderId).orElse(null);



        if(orderEntity==null){
            log.info("order cannot be found");
            return;
        }
        log.info("order found");

        orderEntity.setOrderStatus(status);
   log.info("before order entity saved");
        orderRepository.save(orderEntity);
        log.info("after order entity saved ");




    }

}
