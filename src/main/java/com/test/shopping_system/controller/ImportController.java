package com.test.shopping_system.controller;

import org.springframework.web.bind.annotation.*;

import com.test.shopping_system.service.ProductImportService;

@RestController
@RequestMapping("/import")
public class ImportController {
    private final ProductImportService importService;
    public ImportController(ProductImportService importService) {
        this.importService = importService;
    }

    @PostMapping
    public String importProducts() {
        importService.importProductsFromFakeStore();
        return "Products imported successfully.";
    }
}