package org.trading.productms.exception.exceptions;

/**
 * @author Mehman on 18-07-2023
 * @project customer-ms
 */
public class InsufficientCount extends RuntimeException {
   public InsufficientCount() {
      super("Insufficient item count");
   }
}
