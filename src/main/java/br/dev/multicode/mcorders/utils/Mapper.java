package br.dev.multicode.mcorders.utils;

import br.dev.multicode.mcorders.api.http.requests.ItemOrderRequest;
import br.dev.multicode.mcorders.api.http.requests.PostOrderRequest;
import br.dev.multicode.mcorders.api.http.responses.ItemOrderResponse;
import br.dev.multicode.mcorders.api.http.responses.OrderResponse;
import br.dev.multicode.mcorders.enums.OrderStatus;
import br.dev.multicode.mcorders.entities.Item;
import br.dev.multicode.mcorders.entities.Order;
import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {

  private Mapper() {}

  public static Order postOrderRequestToOrderEntity(final PostOrderRequest postOrderRequest) {
    return Order.builder()
        .userId(postOrderRequest.userId())
        .status(OrderStatus.CREATED)
        .price(postOrderRequest.price())
        .build();
  }

  public static Set<Item> postItemRequestToItemEntity(final Order order, final Set<ItemOrderRequest> itemsRequest) {
    return itemsRequest.stream()
        .map(itemRequest ->
            Item.builder()
                .order(order)
                .productId(itemRequest.productId())
                .amount(itemRequest.amount())
                .price(itemRequest.price())
                .build())
        .collect(Collectors.toSet());
  }

  public static OrderResponse toOrderResponse(final Order order) {
    return new OrderResponse(
        order.getOrderId(),
        order.getUserId(),
        order.getItems().stream()
            .map(Mapper::toItemResponse)
            .collect(Collectors.toSet()),
        order.getPrice());
  }

  private static ItemOrderResponse toItemResponse(final Item item) {
    return new ItemOrderResponse(item.getProductId(), item.getAmount(), item.getPrice());
  }
}
