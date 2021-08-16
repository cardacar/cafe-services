package cafe.quindio.serviceproduct.service;

import cafe.quindio.serviceproduct.entity.Product;
import cafe.quindio.serviceproduct.entity.ProductCategory;
import cafe.quindio.serviceproduct.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IProductService {
    List<Product> listAllProduct();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateproduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(ProductCategory category);
    Product updateStock(Long id, Double quantity);
}
