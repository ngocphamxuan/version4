package com.example.version4.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Float unitPrice;

    @Column(columnDefinition = "nvarchar(200) not null")
    private String image;

    @Column(columnDefinition = "nvarchar(500) not null")
    private String description;

    @Column(nullable = false)
    private Float discount;

    @Temporal(TemporalType.DATE)
    private Date enteredDate;

    @Column(nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
