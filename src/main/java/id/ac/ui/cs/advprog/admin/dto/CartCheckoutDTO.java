package id.ac.ui.cs.advprog.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CartCheckoutDTO {
    private Long id;
    private String userId;
    private List<CartItemsDTO> items = new ArrayList<>();
    private double totalPrice;
    private String status;
}
