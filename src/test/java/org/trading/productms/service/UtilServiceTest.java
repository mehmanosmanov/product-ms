package org.trading.productms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mehman Osmanov
 * @project product-ms
 * @created 23:55 Saturday 22-07-2023
 */
@ExtendWith(MockitoExtension.class)
class UtilServiceTest {

   @InjectMocks
   private UtilService utilService;

   @Test
   void sum() {
      int a = 3;
      int b = 10;

      int result = utilService.sum(a, b);

      int expectedResult = a + b;
      assertEquals(expectedResult, result);

   }
}