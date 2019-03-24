package com.invillia.acme.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_stores")
    private Long id;

    private String name;

    private String address;
}
