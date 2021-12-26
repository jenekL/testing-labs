package uni.labs.testlabs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uni.labs.testlabs.model.DeliveryMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierInfo {

    private Long id;

    private List<SupplyInfo> supplies;

    @NotBlank
    private String companyName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String fathersName;

    @Positive
    private Long bankAccountNumber;

    @NotBlank
    @Pattern(regexp = "^\\+[0-9]{5,13}$")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\+[0-9]{5,13}$")
    private String fax;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private DeliveryMethod deliveryMethod;

}
