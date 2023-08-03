package org.trading.productms.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Mehman Osmanov
 * @project product-ms
 * @created 23:53 Saturday 22-07-2023
 */
@Component
public class UtilService {


   public int sum(int a, int b) {
      return a + b;
   }


}
