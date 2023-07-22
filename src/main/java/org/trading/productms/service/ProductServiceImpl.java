package org.trading.productms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.trading.productms.dto.request.ProductRequest;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.entity.Product;
import org.trading.productms.exception.exceptions.InsufficientCount;
import org.trading.productms.exception.exceptions.NotFoundException;
import org.trading.productms.mapper.ProductMapper;
import org.trading.productms.repository.ProductRepository;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

   private final ProductRepository repository;
   private final ProductMapper mapper;

   @Override
   public void createProduct(ProductRequest request) {
      log.info("Creating a new product");
      repository.save(mapper.dtoToEntity(request));
   }

   @Override
   public ProductResponse getProductById(Long id) {
      log.info("Getting product by id={}", id);
      Product product = repository.findById(id).orElseThrow(
              () -> new NotFoundException("Not found product with such id=" + id));
      return mapper.entityToDto(product);
   }

   @Override
   public boolean decreaseCountByCount(Long id, int count) {
      Product product = repository.findById(id).orElseThrow(
              () -> new NotFoundException("Not found product with such id=" + id));
      if (product.getCount() < count) {
         throw new InsufficientCount();
      }
      var amount = product.getCount() - count;
      product.setCount(amount);
      repository.save(product);
      return true;
   }

   @Override
   public boolean increaseCountByCount(Long id, int count) {
      Product product = repository.findById(id).orElseThrow(
              () -> new NotFoundException("Not found customer with such id=" + id));
      var amount = product.getCount() + count;
      product.setCount(amount);
      repository.save(product);
      return true;
   }
}
