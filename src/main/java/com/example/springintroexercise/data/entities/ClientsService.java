package com.example.springintroexercise.data.entities;

import com.example.springintroexercise.data.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "clients_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsService extends BaseEntity
{
    @Column(unique = true)
    private String description;

    @NotNull
    private BigDecimal price;

    public ClientsService(String id) {
        super(id);
    }

    public  ClientsService(String description, BigDecimal price){
        this.description = description;
        this.price = price;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_services_users",
            joinColumns = @JoinColumn(name = "clients_service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;
}
