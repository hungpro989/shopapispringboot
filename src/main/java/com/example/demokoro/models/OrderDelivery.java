package com.example.demokoro.models;

import com.example.demokoro.dto.OrderDeliveryDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_delivery")
    private String codeDelivery;

    @Column(name = "status")
    private boolean status;

    @Column(name = "data")
    private String data;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="delivery_id", referencedColumnName = "id")
    private Delivery delivery;

    public OrderDelivery(OrderDeliveryDTO dto) {
        this.id=dto.getId();
        this.data=dto.getData();
        this.codeDelivery=dto.getCodeDelivery();
        this.status=dto.isStatus();
    }
}
