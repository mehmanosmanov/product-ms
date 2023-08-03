package org.trading.productms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.trading.productms.entity.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@EnableConfigurationProperties
@EnableJpaRepositories
class ProductRepositoryTest {


   @Autowired
   private ProductRepository productRepository;

   @Test
   @Sql(scripts = "classpath:sql/product.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenFindProductByIdWhenFoundThenReturnEntity() {
      //arrange
      Long id = 1L;

      //act
      Optional<Product> result = productRepository.findById(id);

      //assert
      assertFalse(result.isEmpty());

      Product product = result.get();

      assertEquals(id, product.getId());
      assertEquals(3,product.getCount());
      assertEquals(6.5,product.getPrice());
      assertEquals("Test",product.getProductName());

   }

}