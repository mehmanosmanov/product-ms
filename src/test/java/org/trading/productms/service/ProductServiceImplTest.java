package org.trading.productms.service;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.jdbc.Sql;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.entity.Product;
import org.trading.productms.exception.exceptions.InsufficientCount;
import org.trading.productms.exception.exceptions.NotFoundException;
import org.trading.productms.mapper.ProductMapper;
import org.trading.productms.mapper.ProductMapperImpl;
import org.trading.productms.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Mehman Osmanov
 * @project product-ms
 * @created 11:16 Monday 24-07-2023
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductMapper mapperMock;
    @Mock
//    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setCount(3);
        product.setProductName("Test");
        product.setPrice(6.50);
    }

    @Test
    @Sql(scripts = "classpath:sql/product.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenGetProductByIdWhenFoundTHenReturnEntity() {

        //arrange
        Long id = 1L;

        ProductResponse response = new ProductResponse();
        response.setCount(3);
        response.setPrice(6.50);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(mapperMock.entityToDto(product)).thenReturn(response);

        //act
        ProductResponse productResponse = productService.getProductById(id);

//      assert
        assertNotNull(response);
        assertEquals(product.getPrice(), productResponse.getPrice());
        assertEquals(product.getCount(), productResponse.getCount());
    }

    //
    @Test
    public void givenGetProductByIdWhenNotFoundTHenReturnNotFoundException() {
        // Arrange
        Long id = 100L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThatThrownBy(() -> productService.getProductById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Not found product with such id=" + id);
    }





    @Test
    public void givenDecreaseCountByCountWhenDecreaseThenReturnTrue() {
        //arrange
        Long id = 1L;
        int count = 1;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        //act
        boolean result = productService.decreaseCountByCount(id, count);

        //arrange
        assertTrue(result);
    }

    @Test
    public void givenDecreaseCountByCountWhenNotDecreaseThenReturnNotFoundException() {
        //arrange
        Long id = 100L;
        int count = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        //act arrange
        NotFoundException exception = assertThrows(NotFoundException.class, () -> productService.decreaseCountByCount(id, count));
        assertEquals("Not found product with such id=" + id,exception.getMessage());
    }

    @Test
    public void givenDecreaseCountByCountWhenNotDecreaseThenReturnInsufficientCountException() {
        //arrange
        Long id = 1L;
        int count = 100;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        //act arrange
        InsufficientCount exception = assertThrows(InsufficientCount.class, () -> productService.decreaseCountByCount(id, count));
        assertEquals("Insufficient item count",exception.getMessage());
    }


    @Test
    public void givenIncreaseCountByCountWhenIncreaseThenReturnTrue() {
        //arrange
        Long id = 1L;
        int count = 1;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        //act
        boolean result = productService.increaseCountByCount(id, count);

        //arrange
        assertTrue(result);
    }

    @Test
    public void givenIncreaseCountByCountWhenNotIncreaseThenReturnNotFoundException() {
        //arrange
        Long id = 100L;
        int count = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        //act//arrange
        NotFoundException exception = assertThrows(NotFoundException.class, () -> productService.decreaseCountByCount(id, count));
        assertEquals("Not found product with such id=" + id,exception.getMessage());

    }

}
