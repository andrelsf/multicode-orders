package br.dev.multicode.mcorders.services.impl;

import br.dev.multicode.mcorders.api.http.requests.PostOrderRequest;
import br.dev.multicode.mcorders.api.http.responses.OrderResponse;
import br.dev.multicode.mcorders.entities.Order;
import br.dev.multicode.mcorders.handler.exceptions.NotFoundException;
import br.dev.multicode.mcorders.repositories.ItemRepository;
import br.dev.multicode.mcorders.repositories.OrderRepository;
import br.dev.multicode.mcorders.services.OrderService;
import br.dev.multicode.mcorders.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;

  @Override
  @Transactional(readOnly = true)
  public OrderResponse getOrderById(@NonNull final String orderId) {
    return orderRepository.findById(orderId)
        .map(Mapper::toOrderResponse)
        .orElseThrow(() -> new NotFoundException("Order not found by ID=".concat(orderId)));
  }

  @Override
  @Transactional
  public String createNewOrder(final PostOrderRequest postOrderRequest) {
    final Order order = orderRepository.save(Mapper.postOrderRequestToOrderEntity(postOrderRequest));
    itemRepository.saveAll(Mapper.postItemRequestToItemEntity(order, postOrderRequest.items()));
    return order.getOrderId();
  }
}
