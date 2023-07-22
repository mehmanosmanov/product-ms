package org.trading.productms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.trading.productms.dto.request.ProductRequest;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.service.ProductService;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@RestController
@RequestMapping("/make")
@RequiredArgsConstructor
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

   @PutMapping("/decrease")
   public boolean decreaseCountByCount(@RequestParam Long id, @RequestParam int amount) {
      return productService.decreaseCountByCount(id, amount);
   }

   @PutMapping("/increase")
   public boolean increaseCountByCount(@RequestParam Long id, @RequestParam int amount) {
      return productService.increaseCountByCount(id, amount);
   }

}
