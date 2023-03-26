package br.dev.multicode.mcorders.services;

import br.dev.multicode.mcorders.api.http.requests.PostOrderRequest;
import br.dev.multicode.mcorders.api.http.responses.OrderResponse;

public interface OrderService {

  OrderResponse getOrderById(String orderId);

  String createNewOrder(PostOrderRequest postOrderRequest);
}
