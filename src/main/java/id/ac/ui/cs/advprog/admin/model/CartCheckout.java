package id.ac.ui.cs.advprog.admin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cart_checkout")

public class CartCheckout {

    @Id
    @Column
    private long id;

    @ManyToOne
    private UserEntity user;

    @OneToMany
    private List<CartItems> items;

    @Column
    private double totalPrice;

    @Column
    private String status;
}
