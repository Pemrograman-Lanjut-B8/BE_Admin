package id.ac.ui.cs.advprog.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemsDTO {
    private Long cartId;
    private String bookIsbn;
    private String bookTitle;
    private double price;
    private int quantity;
}
