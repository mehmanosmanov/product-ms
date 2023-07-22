package org.trading.productms.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mehman Osmanov
 * @project product-ms
 * @created 00:57 Wednesday 19-07-2023
 */
@Setter
@Getter
public class ProductRequest {
   private String productName;
   private int count;
   private double price;
}
