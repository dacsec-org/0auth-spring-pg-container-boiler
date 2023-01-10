package org.dacsec.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

/**
 * {@link PostgresEntity} Mock PostgreSQL database table POC, and testing
 * purposes.
 */
@Entity
@Table(appliesTo = "customer")   // This is the table name in the database
public class PostgresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    
    public PostgresEntity() {}
    
    public PostgresEntity(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    
    private Long getId() { return id; }
    private PostgresEntity setId(Long id) {
        this.id = id;
        return this;
    }
    
    private String getName() { return name; }
    private PostgresEntity setName(String name) {
        this.name = name;
        return this;
    }
    
    private int getAge() { return age; }
    private PostgresEntity setAge(int age) {
        this.age = age;
        return this;
    }
    
    private String getAddress() { return address; }
    private PostgresEntity setAddress(String address) {
        this.address = address;
        return this;
    }
}
