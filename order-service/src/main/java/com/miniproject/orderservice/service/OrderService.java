package com.miniproject.orderservice.service;

import com.miniproject.orderservice.dto.OrderLineItemDTO;
import com.miniproject.orderservice.dto.OrderRequest;
import com.miniproject.orderservice.model.Order;
import com.miniproject.orderservice.model.OrderLineItem;
import com.miniproject.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderName(UUID.randomUUID().toString());
        List<OrderLineItem>orderLineItemList = orderRequest.getOrderLineItemDTOList().stream()
                .map(
                        this::mapToDto
                ).toList();
        order.setOrderLineItemList(orderLineItemList);
        orderRepo.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemDTO orderLineItemDTO) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setCode(orderLineItemDTO.getCode());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        return orderLineItem;
    }
}
