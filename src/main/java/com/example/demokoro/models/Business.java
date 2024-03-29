package com.example.demokoro.models;

import com.example.demokoro.dto.BusinessDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "business")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private boolean status;

    @Column(name = "page_id")
    private  String pageId;

    @OneToMany(mappedBy = "business")
    private List<Order> order;

    @ManyToOne()
    @JoinColumn(name = "source_id", nullable=false)
    private Source source;
    public Business(BusinessDTO dto){
        this.id = dto.getId();
        this.codeName=dto.getCodeName();
        this.name=dto.getName();
        this.phone=dto.getPhone();
        this.address=dto.getAddress();
        this.status=dto.isStatus();
        this.pageId=dto.getPageId();
    }
}
