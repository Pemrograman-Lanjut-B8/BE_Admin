package id.ac.ui.cs.advprog.admin.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "Log")
@Entity
@NoArgsConstructor
public class LogAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private  int id;

    @Column(name = "log_string")
    private String logString;

    @ManyToOne
    private CartCheckout cartCheckout;

    @Column
    private LocalDateTime date;

    public LogAdmin(String logString, CartCheckout cartCheckout) {
        this.logString = logString;
        this.cartCheckout = cartCheckout;
        this.date = LocalDateTime.now();
    }

}
