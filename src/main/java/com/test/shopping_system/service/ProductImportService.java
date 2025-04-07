package com.test.shopping_system.service;

import com.test.shopping_system.model.Product;
import com.test.shopping_system.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ProductImportService {

    private final ProductRepository productRepository;

    public ProductImportService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void importProductsFromFakeStore() {
        // Só importa se o banco estiver vazio
        if (productRepository.count() > 0) {
            System.out.println("📦 Produtos já existem no banco. Importação não será feita novamente.");
            return;
        }

        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        ProductDTO[] products = restTemplate.getForObject(url, ProductDTO[].class);

        if (products != null) {
            Arrays.stream(products).forEach(dto -> {
                try {
                    // Verifica se já existe um produto com esse nome
                    Optional<Product> existing = productRepository.findByName(dto.getTitle());
                    if (existing.isPresent()) {
                        System.out.println("⚠️ Produto já existe: " + dto.getTitle());
                        return;
                    }

                    Product p = new Product();
                    p.setName(dto.getTitle());
                    p.setDescription(dto.getDescription());
                    p.setPrice(BigDecimal.valueOf(dto.getPrice()));
                    p.setImage(dto.getImage());
                    p.setStock(100); // valor padrão

                    productRepository.save(p);
                    System.out.println("✅ Produto salvo: " + p.getName());
                } catch (Exception e) {
                    System.out.println("❌ Erro ao salvar produto: " + dto.getTitle());
                    e.printStackTrace();
                }
            });

            System.out.println("🚀 Importação finalizada.");
        } else {
            System.out.println("❌ Falha ao buscar produtos da API.");
        }
    }

    static class ProductDTO {
        private String title;
        private String description;
        private double price;
        private String image;

        // getters e setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }
}
