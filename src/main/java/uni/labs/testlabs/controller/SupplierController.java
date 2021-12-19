package uni.labs.testlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.labs.testlabs.dto.PageResponse;
import uni.labs.testlabs.dto.SupplierInfo;
import uni.labs.testlabs.service.FifthLabService;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    @Autowired
    private FifthLabService fifthLabService;

    @GetMapping
    public ResponseEntity<PageResponse<SupplierInfo>> getAllSuppliers(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "createdAt") String sortField,
                                             @RequestParam(defaultValue = "desc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortField));

        Page<SupplierInfo> suppliersPage = fifthLabService.getAllSuppliers(pageRequest);

        return ResponseEntity.ok(new PageResponse<>(page, suppliersPage.getTotalElements(), pageSize, sortField,
                sortDirection, suppliersPage.getContent()));
    }

    @PostMapping
    public ResponseEntity<?> addSupplier(@RequestBody SupplierInfo supplierInfo) {
        return ResponseEntity.ok(fifthLabService.addSupplier(supplierInfo));
    }

    @PutMapping
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierInfo supplierInfo) {
        return ResponseEntity.ok(fifthLabService.updateSupplier(supplierInfo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        fifthLabService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/supplies")
    public ResponseEntity<?> getAllSupplierSupplies(@PathVariable Long id) {
        return ResponseEntity.ok(fifthLabService.getAllSupplierSupplies(id));
    }

}
