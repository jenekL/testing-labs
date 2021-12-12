package uni.labs.testlabs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("supplies")
public class SuppliesController {

    @GetMapping
    public ResponseEntity<?> getAllSupplies() {

    }

    @PostMapping
    public ResponseEntity<?> addSupply(){

    }

    @PutMapping
    public ResponseEntity<?> updateSupply(){

    }

    @DeleteMapping
    public ResponseEntity<?> deleteSupply() {

    }

}
