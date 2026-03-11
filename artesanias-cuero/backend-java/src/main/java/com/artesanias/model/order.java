package com.artesanias.model;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;


@Entity
@Table(name = "order",
    uniqueConstraints = @UniqueConstraint(columnNames = {"name", "order_id"}))

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id, nullable = false")
    private Customer customer;

    @Column(nullable = false)
    private String status = "draft";
    
    @Column(name = "promised_date")
    private LocalDateTime promisedDate;
    
    @Column(nullable = false)
    private String notes;

    @CreationTimestamp
    @Column (name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Order(){}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getPromisedDate() {
		return promisedDate;
	}
	public void setPromisedDate(LocalDateTime promisedDate) {
		this.promisedDate = promisedDate;
	}

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
       
}


/*
TABLE order {
	id uuid [PRIMARY KEY]
	customer_id uuid [NOT null, ref: > customer.id]
	status text [NOT null, default: "DRAFT"]
	promised_date date
	notes text
	created_at timestamp
}

 */