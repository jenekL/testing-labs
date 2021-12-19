package uni.labs.testlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    private Integer currentPage;
    private Long totalResult;
    private Integer pageSize;
    private String sortField;
    private String sortDirection;
    private List<T> elements;
}
