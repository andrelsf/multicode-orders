package br.dev.multicode.mcorders.api.http.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemOrderRequest(
    @NotBlank String productId,
    @Positive Integer amount,
    @NotNull BigDecimal price
){

}