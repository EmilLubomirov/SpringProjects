package com.example.springintroexercise.data.entities;

import com.example.springintroexercise.data.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer extends BaseEntity
{
    @Column(name = "department_rent")
    private BigDecimal departmentRent;

    @Column(name = "department_type")
    private String apartmentType;

    @Column(name = "agency_commission")
    private BigDecimal agencyCommission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
