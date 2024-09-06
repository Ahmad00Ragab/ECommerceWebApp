package gov.iti.jets.category;

import gov.iti.jets.product.Product;
import gov.iti.jets.system.exceptions.CategoryNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.ObjectNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Set<Category> findAllCategories() {
       return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        Category category=null;
        if(optCategory.isPresent()) {
            category=optCategory.get();
        }else {
            throw new CategoryNotFoundException();
        }
        return category;
    }

    public Category saveCategory(Category category) {
       categoryRepository.save(category);
       return category;
    }

    public Category updateCategory(Category category) {
       categoryRepository.save(category);
       return category;
    }


    public boolean deleteCategory(Long id) {
     return categoryRepository.delete(id);
    }

    public boolean deleteCategory(Category category) {
       return categoryRepository.delete(category);
    }


    public boolean categoryExistsById(Long id) {
        return categoryRepository.existsById(id);
    }

    public long countCategoryWithNamedQuery() {
       return categoryRepository.countWithNamedQuery();
    }

    public long countCategoryWithNamedQuery(String paramName, Category paramValue) {
       return categoryRepository.countWithNamedQuery(paramName, paramValue);
    }

    public List<Product> getProductsByCategory(String category) {
        return categoryRepository.getProductsByCategory(category);
    }


}

