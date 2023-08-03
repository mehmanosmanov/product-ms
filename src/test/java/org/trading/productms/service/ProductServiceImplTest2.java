package org.trading.productms.service;

/**
 * @author Mehman Osmanov
 * @project product-ms
 * @created 18:18 Monday 24-07-2023
 */
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.entity.Product;
import org.trading.productms.exception.exceptions.NotFoundException;
import org.trading.productms.mapper.ProductMapper;
import org.trading.productms.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest2 {

   @InjectMocks
   private ProductServiceImpl productService;

   @Mock
   private ProductMapper mapperMock;

   @Mock
   private ProductRepository productRepository;

   @Test
   void givenGetProductByIdWhenFoundThenReturnProductResponse() {
      // Arrange
      Long id = 1L;
      Product product = new Product();
      product.setCount(11);
      product.setProductName("Test");
      product.setPrice(11);
      product.setId(id);

      ProductResponse expectedResponse = new ProductResponse();
      expectedResponse.setCount(product.getCount());
      expectedResponse.setPrice(product.getPrice());

      when(productRepository.findById(id)).thenReturn(Optional.of(product));
      when(mapperMock.entityToDto(product)).thenReturn(expectedResponse);

      // Act
      ProductResponse actualResponse = productService.getProductById(id);

      // Assert
      assertNotNull(actualResponse);
      assertEquals(expectedResponse.getPrice(), actualResponse.getPrice());
      assertEquals(expectedResponse.getCount(), actualResponse.getCount());

      verify(productRepository, times(1)).findById(id);
      verify(mapperMock, times(1)).entityToDto(product);
   }

   @Test
   void givenGetProductByIdWhenNotFoundThenThrowNotFoundException() {
      // Arrange
      Long id = 1L;

      when(productRepository.findById(id)).thenReturn(Optional.empty());

      // Act & Assert
      assertThrows(NotFoundException.class, () -> productService.getProductById(id));

      verify(productRepository, times(1)).findById(id);
      verifyNoInteractions(mapperMock);
   }
}
