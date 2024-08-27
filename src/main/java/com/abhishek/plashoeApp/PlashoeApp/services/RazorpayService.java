package com.abhishek.plashoeApp.PlashoeApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.razorpay.*;
import org.json.JSONObject;

@Service
@Slf4j
public class RazorpayService {
    private static final String RAZORPAY_API_URL = "orders";
    private static final String RAZORPAY_KEY_ID = "rzp_test_inDqA5vluykl6L";
    private static final String RAZORPAY_KEY_SECRET = "1q260eiyw05tPV4TuJx26o5C";


      public Order createRazorpayOrder(int total) throws RazorpayException{

          RazorpayClient razorpay = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET);


          JSONObject orderRequest = new JSONObject();
          orderRequest.put("amount", total); // Amount in paise
          orderRequest.put("currency", "INR");
          orderRequest.put("receipt", "receipt#1");
          orderRequest.put("payment_capture", 1);


          // Create the order and return the response
          log.info("testiii");
          Order order = razorpay.orders.create(orderRequest);
         log.info("order creatdd "+order);
          return order;

          }



}
