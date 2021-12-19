package uni.labs.testlabs.dto;

import lombok.Data;
import uni.labs.testlabs.model.DeliveryMethod;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SupplierInfo {

    private Long id;

    private List<SupplyInfo> supplies;

    private String companyName;

    private String firstName;

    private String lastName;

    private String fathersName;

    private Long bankAccountNumber;

    private String phone;

    private String fax;

    private BigDecimal price;

    private DeliveryMethod deliveryMethod;

}
