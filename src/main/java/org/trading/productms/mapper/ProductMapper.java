package org.trading.productms.mapper;

import org.mapstruct.Mapper;
import org.trading.productms.dto.request.ProductRequest;
import org.trading.productms.dto.response.ProductResponse;
import org.trading.productms.entity.Product;

/**
 * @author Mehman on 18-07-2023
 * @project product-ms
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

   ProductResponse entityToDto(Product product);

   Product dtoToEntity(ProductRequest request);

}
