package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Page<ProductResponse> getAllProductResponse(int page, int size, String sortBy);

    ProductResponse getProductResponseByID(Integer id);

    ProductResponse createProductResponse(ProductRequest productRequest);

    ProductResponse updateProductResponse(Integer id, ProductRequest productRequest);

    void deleteProductResponse(Integer id);

    Product getProductByID(Integer id);

    List<ProductResponse> getProductsByTagName(String tagName);

}
