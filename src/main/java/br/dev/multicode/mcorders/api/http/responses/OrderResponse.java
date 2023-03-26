package br.dev.multicode.mcorders.api.http.responses;

import java.math.BigDecimal;
import java.util.Set;

public record OrderResponse (
  String orderId,
  String userId,
  Set<ItemOrderResponse> items,
  BigDecimal price
) {

}
