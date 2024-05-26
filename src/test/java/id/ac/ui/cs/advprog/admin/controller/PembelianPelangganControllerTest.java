package id.ac.ui.cs.advprog.admin.controller;

import id.ac.ui.cs.advprog.admin.dto.CartCheckoutAdminDTO;
import id.ac.ui.cs.advprog.admin.service.CartCheckoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PembelianPelangganController.class)
class PembelianPelangganControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartCheckoutService cartCheckoutService;

    private List<CartCheckoutAdminDTO> cartCheckouts;

    @BeforeEach
    void setUp() {
        CartCheckoutAdminDTO cartCheckout1 = new CartCheckoutAdminDTO();
        cartCheckout1.setId(1L);
        cartCheckout1.setNamaUser("User 1");
        cartCheckout1.setEmailUser("user1@example.com");
        cartCheckout1.setPhoneNumberUser("1234567890");
        cartCheckout1.setTotalPrice(100.0);
        cartCheckout1.setStatus("Menunggu Pengiriman Buku");

        CartCheckoutAdminDTO cartCheckout2 = new CartCheckoutAdminDTO();
        cartCheckout2.setId(2L);
        cartCheckout2.setNamaUser("User 2");
        cartCheckout2.setEmailUser("user2@example.com");
        cartCheckout2.setPhoneNumberUser("0987654321");
        cartCheckout2.setTotalPrice(200.0);
        cartCheckout2.setStatus("Dalam Pengiriman");

        cartCheckouts = Arrays.asList(cartCheckout1, cartCheckout2);
    }

    @Test
    void testFindAllCartCheckout() throws Exception {
        when(cartCheckoutService.findAllCartCheckout()).thenReturn(cartCheckouts);

        mockMvc.perform(get("/pembelian-pelanggan/list-cartcheckout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].namaUser").value("User 1"))
                .andExpect(jsonPath("$[0].emailUser").value("user1@example.com"))
                .andExpect(jsonPath("$[0].phoneNumberUser").value("1234567890"))
                .andExpect(jsonPath("$[0].totalPrice").value(100.0))
                .andExpect(jsonPath("$[0].status").value("Menunggu Pengiriman Buku"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].namaUser").value("User 2"))
                .andExpect(jsonPath("$[1].emailUser").value("user2@example.com"))
                .andExpect(jsonPath("$[1].phoneNumberUser").value("0987654321"))
                .andExpect(jsonPath("$[1].totalPrice").value(200.0))
                .andExpect(jsonPath("$[1].status").value("Dalam Pengiriman"));
    }

    @Test
    void testUpdateStatus() throws Exception {
        mockMvc.perform(put("/pembelian-pelanggan/update-status/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
