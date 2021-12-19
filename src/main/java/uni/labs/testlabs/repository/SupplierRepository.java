package uni.labs.testlabs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uni.labs.testlabs.model.Supplier;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {
}
