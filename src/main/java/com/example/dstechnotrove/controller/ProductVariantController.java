package com.example.dstechnotrove.controller;

import com.example.dstechnotrove.domain.ProductVariant;
import com.example.dstechnotrove.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @Autowired
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping()
    public List<ProductVariant> getAllVariants() {
        return productVariantService.getAllVariants();
    }

    @GetMapping("/{sku}")
    public ProductVariant getVariantBySku(@PathVariable String sku) {
        return productVariantService.getVariantBySku(sku);
    }
    @PostMapping
    public ResponseEntity<ProductVariant> createProductVariant(@RequestBody ProductVariant productVariant) {
        ProductVariant newProductVariant = productVariantService.createProductVariant(productVariant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductVariant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(
            @PathVariable Long id, @RequestBody ProductVariant updatedProductVariant) {
        ProductVariant productVariant = productVariantService.updateProductVariant(id, updatedProductVariant);
        if (productVariant != null) {
            return ResponseEntity.ok(productVariant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long id) {
        boolean deleted = productVariantService.deleteProductVariant(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
