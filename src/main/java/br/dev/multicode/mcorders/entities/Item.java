package br.dev.multicode.mcorders.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

  @Id
  @GenericGenerator(
    name = "customUUID",
    strategy = "br.dev.multicode.mcorders.configs.database.CustomUUIDGenerator")
  @GeneratedValue(generator = "customUUID")
  @Column(name = "item_id", length = 33, nullable = false)
  private String itemId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(name = "product_id", nullable = false, length = 33)
  private String productId;

  @Column(nullable = false)
  private Integer amount;

  @Column(nullable = false)
  private BigDecimal price;

}
