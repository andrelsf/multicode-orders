package br.dev.multicode.mcorders.api.http.responses;

import java.math.BigDecimal;

public record ItemOrderResponse(
    String productId,
    Integer amount,
    BigDecimal price
) {

}
