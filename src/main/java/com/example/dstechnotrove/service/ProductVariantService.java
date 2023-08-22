package com.example.dstechnotrove.service;

import com.example.dstechnotrove.domain.ProductVariant;
import com.example.dstechnotrove.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;

    @Autowired
    public ProductVariantService(ProductVariantRepository productVariantRepository) {
        this.productVariantRepository = productVariantRepository;
    }

    public List<ProductVariant> getAllVariants() {
        return productVariantRepository.findAll();
    }

    public ProductVariant getVariantBySku(String sku) {
        return productVariantRepository.findById(sku).orElse(null);
    }
    public ProductVariant createProductVariant(ProductVariant productVariant) {
        return productVariantRepository.save(productVariant);
    }

    public ProductVariant updateProductVariant(Long id, ProductVariant updatedProductVariant) {
        if (productVariantRepository.existsById(String.valueOf(id))) {
            updatedProductVariant.setId(id);
            return productVariantRepository.save(updatedProductVariant);
        }
        return null;
    }

    public boolean deleteProductVariant(Long id) {
        if (productVariantRepository.existsById(String.valueOf(id))) {
            productVariantRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }

}
