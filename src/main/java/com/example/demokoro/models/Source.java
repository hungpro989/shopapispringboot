package com.example.demokoro.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "source")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "data_attribute")
    private String dataAttribute;

    @OneToMany(mappedBy = "source")
    private List<Business> business;
}
