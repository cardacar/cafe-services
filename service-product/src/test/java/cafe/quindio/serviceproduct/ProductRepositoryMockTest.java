package cafe.quindio.serviceproduct;

import cafe.quindio.serviceproduct.entity.Product;
import cafe.quindio.serviceproduct.entity.ProductCategory;
import cafe.quindio.serviceproduct.repository.IProductRepository;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private IProductRepository productRepository;
    public void whenFindByCategory_thenReturnListOfProduct(){
        Product product01 = Product.builder()
                .name("Cafe Honey fresco el pana")
                .category(ProductCategory.builder().id(1L).build())
                .description("El mejor pa")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("25000"))
                .status("CREATED")
                .createAt(new Date()).build();
        productRepository.save(product01);

        List<Product> founds = productRepository.findByCategory(product01.getCategory());
        Assertions.assertThat(founds.size()).isEqualTo(3);

    }
}
