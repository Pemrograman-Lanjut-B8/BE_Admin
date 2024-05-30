package id.ac.ui.cs.advprog.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class CartCheckoutAdminDTO {
    private Long id;
    private String namaUser;
    private String emailUser;
    private String phoneNumberUser;
    private List<CartItemsDTO> items;
    private double totalPrice;
    private String status;

}
