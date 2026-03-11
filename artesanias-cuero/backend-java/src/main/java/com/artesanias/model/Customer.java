package com.artesanias.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "customer",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "order_id"}))

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id, nullable = false")
    private Customer customer;

    @CreationTimestamp
    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Customer(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

/*
Table customer {
  id SERIAL [pk]
  name TEXT [not null, unique]
  phone VARCHAR
  email TEXT [not null, unique]
  address TEXT
  created_at TIMESTAMP [default: `CURRENT_TIMESTAMP`]
}
*/