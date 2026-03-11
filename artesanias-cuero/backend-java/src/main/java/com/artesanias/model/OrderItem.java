package com.artesanias.model;
import jakarta.persistence.*;

@Entity
@Table(name = "order_item",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "order_item_id"}))
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id, nullable = false")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id, nullable = false")
    private Product product;

    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private double price;
 
    public OrderItem(){}

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public Order getOrder() {
      return order;
    }

    public void setOrder(Order order) {
      this.order = order;
    }

    public Product getProduct() {
      return product;
    }

    public void setProduct(Product product) {
      this.product = product;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }


 
}

/*
Table order_item {
  id uuid [PRIMARY KEY]
  order_id uuid [NOT null, ref: > order.id]
  product_id uuid [NOT null, ref: > product.id]
  color_id uuid [NOT null, ref: > color.id]
  quantity int [NOT null]
  price numeric(14,2)
}
*/