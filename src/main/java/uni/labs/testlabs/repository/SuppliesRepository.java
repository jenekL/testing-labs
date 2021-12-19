package uni.labs.testlabs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uni.labs.testlabs.model.Supply;

@Repository
public interface SuppliesRepository extends PagingAndSortingRepository<Supply, Long> {
}
