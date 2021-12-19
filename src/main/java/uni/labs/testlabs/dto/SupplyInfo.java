package uni.labs.testlabs.dto;

import lombok.Data;
import uni.labs.testlabs.model.SupplyType;

import java.math.BigDecimal;

@Data
public class SupplyInfo {

    private Long id;

    private Long supplierId;

    private SupplyType type;

    private String mark;

    private Integer delay;

    private BigDecimal price;

}
