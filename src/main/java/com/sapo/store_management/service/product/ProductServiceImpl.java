package com.sapo.store_management.service.product;

import com.sapo.store_management.dto.product.ProductRequest;
import com.sapo.store_management.dto.product.ProductResponse;
import com.sapo.store_management.mapper.ProductMapper;
import com.sapo.store_management.model.Product;
import com.sapo.store_management.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    private final ProductRepo productRepo;


    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Page<ProductResponse> getAllProductResponse(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Product> response = productRepo.findAll(pageable);
        return response.map(ProductMapper::convertProductResponse);
    }

    @Override
    public ProductResponse getProductResponseByID(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        ProductResponse productResponse = ProductMapper.convertProductResponse(product);
        return productResponse;
    }

    @Override
    public ProductResponse createProductResponse(ProductRequest productRequest) {
        Product product = productMapper.convertProduct(productRequest);
        product = productRepo.save(product);
        return ProductMapper.convertProductResponse(product);
    }

    @Override
    public ProductResponse updateProductResponse(Integer id, ProductRequest productRequest) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        Product update = productMapper.convertProduct(productRequest);
        update = productRepo.save(update);
        return ProductMapper.convertProductResponse(update);

    }

    @Override
    public void deleteProductResponse(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Can not find product"));
        productRepo.delete(product);
    }

    @Override
    public Product getProductByID(Integer id) {
        return null;
    }

    @Override
    public List<ProductResponse> getProductsByTagName(String tagName) {
        List<Product> product = productRepo.findByTagName(tagName);
        return product.stream()
                .map(ProductMapper::convertProductResponse)
                .collect(Collectors.toList());
    }
}
