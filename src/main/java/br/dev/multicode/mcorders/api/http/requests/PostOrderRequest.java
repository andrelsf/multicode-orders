package br.dev.multicode.mcorders.api.http.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

public record PostOrderRequest(
    @NotBlank String userId,
    @NotEmpty Set<ItemOrderRequest> items,
    @NotNull @Positive BigDecimal price
) {

}
