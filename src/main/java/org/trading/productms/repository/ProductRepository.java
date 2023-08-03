package org.trading.productms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.trading.productms.entity.Product;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
