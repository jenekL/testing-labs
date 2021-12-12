package uni.labs.testlabs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("supplier")
public class SupplierController {

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {

    }

    @PostMapping
    public ResponseEntity<?> addSupplier() {

    }

    @PutMapping
    public ResponseEntity<?> updateSupplier() {

    }

    @DeleteMapping
    public ResponseEntity<?> deleteSupplier() {

    }

    @GetMapping("supplies")
    public ResponseEntity<?> getAllSupplierSupplies() {

    }

    @PostMapping("supplies")
    public ResponseEntity<?> addSupplyToSupplier() {

    }

    @DeleteMapping("supplies")
    public ResponseEntity<?> deleteSupplyFromSupplier() {

    }

}
