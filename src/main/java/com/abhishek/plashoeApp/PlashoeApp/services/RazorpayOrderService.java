package com.abhishek.plashoeApp.PlashoeApp.services;


import com.abhishek.plashoeApp.PlashoeApp.dto.OrderDTO;
import com.abhishek.plashoeApp.PlashoeApp.entities.CartEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.OrderEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.OrderItemEntity;
import com.abhishek.plashoeApp.PlashoeApp.entities.UserEntity;
import com.abhishek.plashoeApp.PlashoeApp.repositories.CartRepository;
import com.abhishek.plashoeApp.PlashoeApp.repositories.OrderRepository;
import com.abhishek.plashoeApp.PlashoeApp.repositories.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RazorpayOrderService {

    private static final Logger log = LoggerFactory.getLogger(RazorpayOrderService.class);
    private final CartRepository cartRepository;
   private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  private final RazorpayService razorpayService;



   @Transactional
    public Order createOrder() throws RazorpayException {
       UserEntity userEntity = userRepository.findById(1L).orElse(null);



       List<CartEntity> cartEntities = cartRepository.findByUser(userEntity);

       OrderEntity order = new OrderEntity();
       order.setUser(userEntity);
       order.setOrderStatus("Pending");
       Double total=0.0;

       for(CartEntity cart : cartEntities){
           total = total + cart.getSubTotal();
       }

       order.setTotal(total);

       order.setShippingAddress(userEntity.getAddress());
       order.setPhoneNumber(userEntity.getPhoneNo());

       List<OrderItemEntity> orderItems = new ArrayList<>();

       for(CartEntity cart :  cartEntities){
           OrderItemEntity orderItem = new OrderItemEntity();
           orderItem.setOrder(order);
           orderItem.setProduct(cart.getProduct());
           orderItem.setSubTotal(cart.getSubTotal());
           orderItems.add(orderItem);
       }

       order.setOrderItems(orderItems);
       OrderEntity savedOrder = orderRepository.save(order);
       log.info(savedOrder.getTotal()+"");
        Order o = razorpayService.createRazorpayOrder((int) savedOrder.getTotal()*100 );
        savedOrder.setOrderStatus(o.get("status"));
        savedOrder.setRazorpayId(o.get("id"));
        orderRepository.save(savedOrder);
        return o;
    }

}
