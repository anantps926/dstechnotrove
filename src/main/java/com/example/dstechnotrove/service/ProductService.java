package com.example.dstechnotrove.service;

import com.example.dstechnotrove.domain.Product;
import com.example.dstechnotrove.domain.ProductVariant;
import com.example.dstechnotrove.exception.ResourceNotFoundException;
import com.example.dstechnotrove.repository.CategoryRepository;
import com.example.dstechnotrove.repository.ProductRepository;
import com.example.dstechnotrove.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, ProductVariantRepository productVariantRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
        this.categoryRepository = categoryRepository;
    }
//    @Autowired
//    public ProductService(ProductRepository productRepository, ProductVariantRepository productVariantRepository,
//                          CategoryRepository categoryRepository) {
//        this.productRepository = productRepository;
//        this.productVariantRepository = productVariantRepository;
//        this.categoryRepository = categoryRepository;
//    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductVariant> getProductVariants(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        return productVariantRepository.findByProduct(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Other methods for creating, updating, and deleting products and categories
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            // Update existingProduct with values from updatedProduct
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCategory(updatedProduct.getCategory());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
