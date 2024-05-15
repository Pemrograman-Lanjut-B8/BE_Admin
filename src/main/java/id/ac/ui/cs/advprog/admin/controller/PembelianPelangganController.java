//package id.ac.ui.cs.advprog.admin.controller;
//
//import id.ac.ui.cs.advprog.admin.model.Pembelian;
//import id.ac.ui.cs.advprog.admin.service.PembelianService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;

//@RestController
//@RequestMapping("/pembelian-pelanggan")
//public class PembelianPelangganController {
//
//    @Autowired
//    private PembelianService pembelianService;
//
//    @GetMapping("/")
//    public List<Pembelian> getAllPembelianPelanggan() {
//        return pembelianService.findAllPembelianPelanggan();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Pembelian> getPembelianPelangganById(@PathVariable Long id) {
//        return pembelianService.findPembelianPelangganById(id);
//    }
//
//    @GetMapping("/email/{email}")
//    public List<Pembelian> getPembelianPelangganByEmail(@PathVariable String email) {
//        return pembelianService.findPembelianPelangganByEmail(email);
//    }
//
//    @GetMapping("book/{book}")
//    public List<Pembelian> getPembelianPelangganByBook(@PathVariable String book) {
//        return pembelianService.findPembelianPelangganByBook(book);
//    }
//
//    @PostMapping("{id}/update-status")
//    public void updateStatus(@PathVariable Long id, @RequestBody String status) {
//        pembelianService.updateStatus(id, status);
//    }
//}