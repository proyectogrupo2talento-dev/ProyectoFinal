package com.artesanias.model;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_inventory",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "order_id"}))
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id, nullable = false")
    private Product product;

    @Column(nullable = false)
    private double qty_on_hand;

    public ProductInventory(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQty_on_hand() {
        return qty_on_hand;
    }

    public void setQty_on_hand(double qty_on_hand) {
        this.qty_on_hand = qty_on_hand;
    }

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}