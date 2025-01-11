package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.option.OptionRequest;
import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.model.Variant;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductResponse> getAllProductResponse(int page, int size, String sortBy);

    ProductResponse getProductResponseByID(Integer id);

    ProductResponse createProductResponse(ProductRequest productRequest);

    ProductResponse updateProductResponse(Integer id, ProductRequest productRequest);

    void deleteProductResponse(Integer id);


    List<ProductResponse> getProductsByTagName(String tagName);

   ProductResponse generateVariantsForProduct(Product product , List<OptionRequest> inputOptions);

   Product getProductById(Integer id);

}
