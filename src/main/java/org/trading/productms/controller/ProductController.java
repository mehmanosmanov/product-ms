package org.trading.productms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.trading.productms.dto.request.ProductRequest;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.service.ProductService;
import org.trading.productms.service.ProductServiceImpl;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

   private final ProductService productService;

   @PostMapping("/")
   public void createProduct(@RequestBody ProductRequest request) {
      productService.createProduct(request);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
      return ResponseEntity.ok(productService.getProductById(id));
   }

   @GetMapping("/decrease/{id}/{count}")
   public ResponseEntity<Boolean> decreaseCountByCount(@PathVariable Long id, @PathVariable int count) {
      return ResponseEntity.ok(productService.decreaseCountByCount(id, count));
   }

   @GetMapping("/increase/{id}/{count}")
   public ResponseEntity<Boolean> increaseCountByCount(@PathVariable Long id, @PathVariable int count) {
      return ResponseEntity.ok(productService.increaseCountByCount(id, count));
   }


   private final ProductServiceImpl serImpl;

//   @GetMapping("/circuit/{id}")
//   public ProductResponse circuitMethod(@PathVariable Long id) {
//      return serImpl.circuit(id);
//   }
}
