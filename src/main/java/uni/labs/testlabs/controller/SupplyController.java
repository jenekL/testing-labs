package uni.labs.testlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.labs.testlabs.dto.SupplyInfo;
import uni.labs.testlabs.service.FifthLabService;

import javax.validation.Valid;

@RestController
@RequestMapping("supplies")
public class SupplyController {

    @Autowired
    private FifthLabService fifthLabService;

    @PostMapping
    public ResponseEntity<?> addSupply(@Valid @RequestBody SupplyInfo supplyInfo) {
        return ResponseEntity.ok(fifthLabService.addSupplyToSupplier(supplyInfo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSupply(@PathVariable Long id) {
        fifthLabService.deleteSupply(id);
        return ResponseEntity.ok().build();
    }

}
