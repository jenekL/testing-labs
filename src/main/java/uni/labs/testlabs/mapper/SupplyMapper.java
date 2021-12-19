package uni.labs.testlabs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uni.labs.testlabs.dto.SupplierInfo;
import uni.labs.testlabs.dto.SupplyInfo;
import uni.labs.testlabs.model.Supplier;
import uni.labs.testlabs.model.Supply;

@Mapper
public interface SupplyMapper {

    SupplyInfo fromModel(Supply supply);

    @Mapping(target = "supplier", ignore = true)
    Supply toModel(SupplyInfo supplyInfo);

    Supply toModel(@MappingTarget Supply supply, SupplyInfo supplyInfo);

    SupplierInfo fromModel(Supplier supplier);

    Supplier toModel(SupplierInfo supplierInfo);

    Supplier toModel(@MappingTarget Supplier supplier, SupplierInfo supplierInfo);
}
