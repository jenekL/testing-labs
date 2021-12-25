package uni.labs.testlabs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uni.labs.testlabs.dto.SupplierInfo;
import uni.labs.testlabs.dto.SupplyInfo;
import uni.labs.testlabs.mapper.SupplyMapper;
import uni.labs.testlabs.model.DeliveryMethod;
import uni.labs.testlabs.model.Supplier;
import uni.labs.testlabs.model.Supply;
import uni.labs.testlabs.model.SupplyType;
import uni.labs.testlabs.repository.SupplierRepository;
import uni.labs.testlabs.repository.SuppliesRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FifthLabService {

    private static final int VALUES_AMOUNT = 10000;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SuppliesRepository suppliesRepository;

    @Autowired
    private SupplyMapper supplyMapper;

//    @PostConstruct
    public void init() {

        Random random = new Random();

        if (suppliesRepository.count() > 0) {
            return;
        }

        log.info("Start creating fixtures suppliers");
        for (int i = 0; i < VALUES_AMOUNT; i++) {
            Supplier supplier = new Supplier();
            supplier.setCompanyName("Company " + i);
            supplier.setFirstName("Firstname " + i);
            supplier.setLastName("Lastname " + i);
            supplier.setFathersName("FathersName " + i);
            supplier.setBankAccountNumber(10000000L + i);
            supplier.setPhone("+38071123" + i);
            supplier.setFax("+38071124" + i);
            supplier.setPrice(new BigDecimal(i));
            supplier.setDeliveryMethod(i % 2 == 0 ? DeliveryMethod.COURIER : DeliveryMethod.POST);

            supplierRepository.save(supplier);

            if (i % 100 == 0) {
                log.info("{} suppliers created", i);
            }
        }

        log.info("Start creating fixtures supplies");
        for (int i = 0; i < VALUES_AMOUNT; i++) {
            Supply supply = new Supply();
            supply.setSupplier(supplierRepository.findById((long) ((i + 1) / 100)).orElse(null));
            supply.setType(i % 2 == 0 ? SupplyType.PACKETS : SupplyType.T_SHIRTS);
            supply.setMark("Brand " + i);
            supply.setDelay(random.nextInt(1000));
            supply.setPrice(new BigDecimal(i));

            suppliesRepository.save(supply);

            if (i % 100 == 0) {
                log.info("{} supplies created", i);
            }
        }
    }

    public Page<SupplierInfo> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable)
                .map(supplyMapper::fromModel);
    }

    public SupplierInfo addSupplier(SupplierInfo supplierInfo) {
        Supplier supplier = supplyMapper.toModel(supplierInfo);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplyMapper.fromModel(savedSupplier);
    }

    public SupplierInfo updateSupplier(SupplierInfo supplierInfo) {
        Supplier existingSupplier = supplierRepository.findById(supplierInfo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Can not find"));

        Supplier updatedSupplier = supplyMapper.toModel(existingSupplier, supplierInfo);

        return supplyMapper.fromModel(supplierRepository.save(updatedSupplier));
    }

    public void deleteSupplier(long supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    public SupplyInfo addSupplyToSupplier(SupplyInfo supplyInfo) {
        Supplier existingSupplier = supplierRepository.findById(supplyInfo.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Can not find"));

        Supply newSupply = supplyMapper.toModel(supplyInfo);
        newSupply.setSupplier(existingSupplier);

        return supplyMapper.fromModel(suppliesRepository.save(newSupply));
    }

    public List<SupplyInfo> getAllSupplierSupplies(long supplierId) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find"));

        return existingSupplier.getSupplies().stream()
                .map(supplyMapper::fromModel)
                .collect(Collectors.toList());
    }

    public void deleteSupply(long supplyId) {
        suppliesRepository.deleteById(supplyId);
    }

}
