package cafe.quindio.serviceproduct.repository;

import cafe.quindio.serviceproduct.entity.Product;
import cafe.quindio.serviceproduct.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategory(ProductCategory category);
}
