package thymeleafexamples.gtvg.business.services;

import thymeleafexamples.gtvg.business.entites.Product;
import thymeleafexamples.gtvg.business.entites.repositories.ProductRepository;

import java.util.List;

public class ProductService {

    public ProductService() {
        super();
    }

    public List<Product> findAll() {
        return ProductRepository.getInstance().findAll();
    }

    public Product findById(final Integer id) {
        return ProductRepository.getInstance().findById(id);
    }

}
