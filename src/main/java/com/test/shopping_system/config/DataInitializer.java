package com.test.shopping_system.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.test.shopping_system.service.ProductImportService;

@Component
public class DataInitializer implements ApplicationRunner {

    private final ProductImportService productImportService;

    public DataInitializer(ProductImportService productImportService) {
        this.productImportService = productImportService;
    }

    @Override
    public void run(ApplicationArguments args) {
        productImportService.importProductsFromFakeStore();
        System.out.println("✅ Produtos importados automaticamente ao iniciar a aplicação.");
    }
}
