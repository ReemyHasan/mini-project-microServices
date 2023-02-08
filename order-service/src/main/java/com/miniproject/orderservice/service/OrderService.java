package com.miniproject.orderservice.service;

import com.miniproject.orderservice.dto.InventoryResponse;
import com.miniproject.orderservice.dto.OrderLineItemDTO;
import com.miniproject.orderservice.dto.OrderRequest;
import com.miniproject.orderservice.model.Order;
import com.miniproject.orderservice.model.OrderLineItem;
import com.miniproject.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepo orderRepo;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderName(UUID.randomUUID().toString());
        List<OrderLineItem>orderLineItemList = orderRequest.getOrderLineItemDTOList().stream()
                .map(
                        this::mapToDto
                ).toList();
        order.setOrderLineItemList(orderLineItemList);
        List<String> OrderListCodes = order.getOrderLineItemList().stream().map(
                OrderLineItem::getCode
        ).toList();
        // Call Inventory Service aSynchronously and accept order if there is the ordered amount of it in Stock
        InventoryResponse[] res = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("code",OrderListCodes).build())
                .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                                .block();
        boolean AllProductInStock = Arrays.stream(res).allMatch(InventoryResponse::isInStock);
        if(AllProductInStock){
            orderRepo.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in Stock, please try again later");
        }

    }

    private OrderLineItem mapToDto(OrderLineItemDTO orderLineItemDTO) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDTO.getPrice());
        orderLineItem.setCode(orderLineItemDTO.getCode());
        orderLineItem.setQuantity(orderLineItemDTO.getQuantity());
        return orderLineItem;
    }
}
