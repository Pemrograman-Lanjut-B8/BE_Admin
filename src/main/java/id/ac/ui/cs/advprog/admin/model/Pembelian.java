package id.ac.ui.cs.advprog.admin.model;

import id.ac.ui.cs.advprog.admin.model.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pembelian")
public class Pembelian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_pembeli")
    private String emailPembeli;

    @Column(name = "nama_buku")
    private String namaBuku;

    @Column(name = "total_harga")
    private BigDecimal hargaTotal;

    @Column(name = "status")
    private String status;

    // Getters dan setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailPembeli() {
        return emailPembeli;
    }

    public void setEmailPembeli(String emailPembeli) {
        this.emailPembeli = emailPembeli;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public BigDecimal getHargaTotal() {
        return hargaTotal;
    }

    public void setHargaTotal(BigDecimal hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}