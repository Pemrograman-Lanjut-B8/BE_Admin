package id.ac.ui.cs.advprog.admin.model.interfaces;

import java.time.LocalDate;

public interface BookBuilder {
    void reset();
    void setJudulBuku(String judulBuku);
    void setPenulis(String penulis);
    void setPenerbit(String penerbit);
    void setDeskripsi(String deskripsi);
    void setHarga(double harga);
    void setStok(int stok);
    void setTanggalTerbit(LocalDate tanggalTerbit);
    void setIsbn(String isbn);
    void setJumlahHalaman(int jumlahHalaman);
    void setFotoCover(String fotoCover);
    void setKategori(String kategori);
//    void setRating(double rating);
}
