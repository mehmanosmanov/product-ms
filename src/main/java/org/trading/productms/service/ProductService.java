package org.trading.productms.service;

import org.springframework.stereotype.Service;
import org.trading.productms.dto.request.ProductRequest;
import org.trading.productms.dto.response.ProductResponse;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Service
public interface ProductService {

   void createProduct(ProductRequest request);
   ProductResponse getProductById(Long id);

   boolean decreaseCountByCount(Long id, int count);
   boolean increaseCountByCount(Long id, int count);

}
