package gov.iti.jets.services;


import gov.iti.jets.services.dtos.ProductDto;
import gov.iti.jets.models.Category;
import gov.iti.jets.models.Product;
import gov.iti.jets.repositories.ProductRepository;
import gov.iti.jets.system.exceptions.ProductNotFoundException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }


    public Set<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> optProduct = productRepository.findById(id);
        Product product=null;
        if(optProduct.isPresent()) {
            product=optProduct.get();
        }else {
            throw new ProductNotFoundException();
        }
        return optProduct;
    }

    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product ;
    }

    public Product updateProduct(Product product) {
        // productRepository.save(product);
        /* Commented the above code, we should use productRepository.update not productRepository.save(product) : Haroun */
        productRepository.update(product);
        return product;
    }


    @Transactional
    public void createProduct(Product product) {
        System.out.println("Saving product: " + product);
        productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        System.out.println("inside ProductService.deleteProduct");
        return productRepository.delete(id);
    }

    public boolean deleteProduct(Product product) {
        return productRepository.delete(product);
    }


    public boolean productExistsById(Long id) {
        return productRepository.existsById(id);
    }

    public long countProductWithNamedQuery() {
        return productRepository.countWithNamedQuery();
    }

    public long countProductWithNamedQuery(String paramName, Product paramValue) {
        return productRepository.countWithNamedQuery(paramName, paramValue);
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

    public Set<ProductDto> findAllProductsUsingDTO(int pageNumber, int pageSize) {
       return productRepository.findAllProductsUsingDTO(pageNumber,pageSize);
    }

    public Set<ProductDto> findProductsByCategoryUsingProductDTO(String category, int pageNumber, int pageSize) {
        return productRepository.findProductsByCategoryUsingProductDTO(category, pageNumber, pageSize);//String /
    }


    public Set<ProductDto> findProductByNameUsingProductDTO(String name, int pageNumber, int pageSize) {
       return productRepository.findProductByNameUsingProductDTO(name, pageNumber,pageSize);
    }


    public int countByName(String name) {
        return productRepository.countProductsByName(name);
    }

    public int countProductsByCategory(String category) {
       return productRepository.countProductsByCategory(category);
    }


    public int countAllProducts(){
        return productRepository.countAllProducts();
    }

    public Set<ProductDto> sortProductsByCategoryAndPriceUsingProductDTO(String category, int pageNumber, int pageSize) {
        return productRepository.sortProductsByCategoryAndPriceUsingProductDTO(category,pageNumber,pageSize);
    }
}
