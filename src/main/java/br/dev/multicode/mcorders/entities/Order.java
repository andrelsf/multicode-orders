package br.dev.multicode.mcorders.entities;

import br.dev.multicode.mcorders.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GenericGenerator(
    name = "customUUID",
    strategy = "br.dev.multicode.mcorders.configs.database.CustomUUIDGenerator")
  @GeneratedValue(generator = "customUUID")
  @Column(name = "order_id", length = 33, nullable = false)
  private String orderId;

  @Column(name = "user_id", length = 33, nullable = false)
  private String userId;

  @Enumerated(EnumType.STRING)
  @Column(length = 30, nullable = false)
  private OrderStatus status;

  @OneToMany(
    mappedBy = "order",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
  private Set<Item> items = new HashSet<>();

  @Column(nullable = false)
  private BigDecimal price;

  @CreationTimestamp
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  private ZonedDateTime updatedAt;

}
