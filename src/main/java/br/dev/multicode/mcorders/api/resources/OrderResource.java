package br.dev.multicode.mcorders.api.resources;

import br.dev.multicode.mcorders.api.http.requests.PostOrderRequest;
import br.dev.multicode.mcorders.api.http.responses.OrderResponse;
import br.dev.multicode.mcorders.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(
  value = "/orders",
  consumes = MediaType.APPLICATION_JSON_VALUE,
  produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderResource {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Void> postOrder(@RequestBody @Valid final PostOrderRequest postOrderRequest) {
    final String orderId = orderService.createNewOrder(postOrderRequest);
    final URI uriLocation = UriComponentsBuilder.fromUriString("/api/v1/orders/{orderId}")
        .buildAndExpand(orderId)
        .toUri();
    return ResponseEntity.created(uriLocation).build();
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> getOrder(@PathVariable @NotBlank final String orderId) {
    final OrderResponse orderResponse = orderService.getOrderById(orderId);
    return ResponseEntity.ok(orderResponse);
  }
}
