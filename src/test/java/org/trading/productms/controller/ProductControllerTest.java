package org.trading.productms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.trading.productms.ProductMsApplication;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.exception.ErrorResponse;
import org.trading.productms.exception.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ProductMsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ProductControllerTest {

   @LocalServerPort
   private int serverPort;

   private String url;

   @BeforeEach
   void setUp() {
      url = "http://localhost:" + serverPort + "/product";
   }


   @Autowired
   private TestRestTemplate restTemplate;

   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenGetProductByIdWhenFoundThenReturnEntity() {
      //arrange
      Long id = 1L;
      //act
      ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url + "/" + id, ProductResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals(HttpStatus.OK, response.getStatusCode());
      final ProductResponse product = response.getBody();
      assertEquals(3, product.getCount());
      assertEquals(6.5, product.getPrice());
   }

   @Test
   public void givenGetProductByIdWhenNotFoundThenReturnException() {
      //arrange
      Long id = 1L;
      //act
      ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url + "/" + id, ErrorResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
      assertEquals("Not found product with such id=" + id, response.getBody().getErrorMessage());
   }


   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseCountByCountWhenDecreasedThenReturnTrue() {
      //arrange
      Long id = 1L;
      int count = 1;
      //act
      ResponseEntity<Boolean> response = restTemplate.getForEntity(url + "/decrease/" + id + "/" + count, Boolean.class);
      //assert
      assertNotNull(response.getBody());
      assertTrue(response.getBody());
   }


   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseCountByCountWhenNotDecreasedThenReturnNotFoundException() {
      //arrange
      Long id = 100L;
      int count = 1;
      //act
      ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url + "/decrease/" + id + "/" + count, ErrorResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals("Not found product with such id=" + id, response.getBody().getErrorMessage());
   }

   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenDecreaseCountByCountWhenNotDecreasedThenReturnInsufficientCount() {
      //arrange
      Long id = 1L;
      int count = 100;
      //act
      ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url + "/decrease/" + id + "/" + count, ErrorResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals("Insufficient item count", response.getBody().getErrorMessage());
   }


   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenIncreaseCountByCountWhenIncreasedThenReturnResult() {
      //arrange
      Long id = 1L;
      int count = 1;
      //act                                            ?????????
      ResponseEntity<Boolean> response = restTemplate.getForEntity(url + "/increase/" + id + "/" + count, Boolean.class);
      //assert
      assertNotNull(response.getBody());
      assertTrue(response.getBody());
   }

   @Test
   @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
   public void givenIncreaseCountByCountWhenIncreasedThenReturnNotFoundException() {
      //arrange
      Long id = 100L;
      int count = 1;
      //act                                            ?????????
      ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url + "/increase/" + id + "/" + count, ErrorResponse.class);
      //assert
      assertNotNull(response.getBody());
      assertEquals(404, response.getBody().getErrorCode());

   }


}