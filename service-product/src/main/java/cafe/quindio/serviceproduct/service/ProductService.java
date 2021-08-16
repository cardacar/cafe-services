package cafe.quindio.serviceproduct.service;

import cafe.quindio.serviceproduct.entity.Product;
import cafe.quindio.serviceproduct.entity.ProductCategory;
import cafe.quindio.serviceproduct.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
public class ProductService implements IProductService {


    private final IProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (!productDB.equals(null)) {
            return productDB;
        }
        product.setStatus("CREATED");
        productDB = productRepository.save(product);
        return productDB;
    }

    @Override
    public Product updateproduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (productDB.equals(null)) {
            return productDB;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if (productDB.equals(null)) {
            return productDB;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if (productDB.equals(null)) {
            return productDB;
        }
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }
}
