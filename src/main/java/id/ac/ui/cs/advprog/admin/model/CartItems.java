package id.ac.ui.cs.advprog.admin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    private Book book;

    @Column
    private int quantity;

    @ManyToOne
    private UserEntity user;
}
