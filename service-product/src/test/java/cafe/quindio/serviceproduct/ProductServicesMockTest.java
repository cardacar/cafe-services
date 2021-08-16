package cafe.quindio.serviceproduct;

import cafe.quindio.serviceproduct.entity.Product;
import cafe.quindio.serviceproduct.entity.ProductCategory;
import cafe.quindio.serviceproduct.repository.IProductRepository;
import cafe.quindio.serviceproduct.service.IProductService;
import cafe.quindio.serviceproduct.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServicesMockTest {
    @Mock                                           //en esta ocacion se trabaja con datos mock en vez de H2
    private IProductRepository productRepository;
    private IProductService productService;

    @BeforeEach                                     //Esto se tiene que ejecutar antes de realizar el test
    public void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
        Product computer =  Product.builder()
                .id(1L)
                .name("computer")
                .category(ProductCategory.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct() {
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {
        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
