package gov.iti.jets.product;


import gov.iti.jets.system.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductByName(String name) {
        Optional<Product> optProduct=productRepository.getProductByName(name);
        Product product=null;
        if(optProduct.isPresent()){
            product=optProduct.get();
        }else {
            throw  new ProductNotFoundException();
        }
        return product;
    }



    public Set<Product> findProductsByCategory(String category) {
        return  productRepository.findProductsByCategory(category);
    }


    public Set<Product> findProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductsByPrice(minPrice, maxPrice);
    }



    public Set<Product> sortProductsByPrice() {
        return productRepository.sortProductsByPrice();
    }


    public Set<Product> sortProductsByCategoryAndPrice(String category) {
        return productRepository.sortProductsByCategoryAndPrice(category);
    }

}
