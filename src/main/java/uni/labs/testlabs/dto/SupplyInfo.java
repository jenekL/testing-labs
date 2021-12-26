package uni.labs.testlabs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uni.labs.testlabs.model.SupplyType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplyInfo {

    private Long id;

    @Positive
    @NotNull
    private Long supplierId;

    @NotNull
    private SupplyType type;

    @NotBlank
    private String mark;

    @Positive
    @NotNull
    private Integer delay;

    @Positive
    @NotNull
    private BigDecimal price;

}
