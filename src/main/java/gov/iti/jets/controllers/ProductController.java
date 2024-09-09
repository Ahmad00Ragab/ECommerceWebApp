package gov.iti.jets.controllers;

import com.google.gson.Gson;

import gov.iti.jets.repositories.CategoryRepository;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.util.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import gov.iti.jets.models.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDateTime;



@WebServlet("/ProductController")
public class ProductController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("ProductController");
        } else if ("fetch".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = productService.findProductById(id);

            Gson gson = new Gson();
            String productJson = gson.toJson(product.orElse(null));
            resp.setContentType("application/json");
            resp.getWriter().write(productJson);
        } else {
            Set<Product> products = productService.findAllProducts();
            req.setAttribute("productList", products);

            /* Fetch and set categories */
            CategoryService categoryService = new CategoryService(new CategoryRepository(Category.class));
            Set<Category> categories = categoryService.findAllCategories();
            req.setAttribute("categories", categories);

            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("Inside the doPost");
        
        String productId = req.getParameter("productId");
        
        System.out.println(productId);

        String name      = req.getParameter("name");
        System.out.println(productId);
        
        double price;
        int quantity;
        
        try {
            price    = Double.parseDouble(req.getParameter("price"));
            
            System.out.println("Price : "+price);

            quantity = Integer.parseInt(req.getParameter("quantity"));
            System.out.println("Quantity : "+quantity);

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format for price or quantity.");
            
            System.out.println("error in price or quantity");

            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
    
        String categoryId = req.getParameter("category");

        System.out.println("Category Id : " + categoryId);

        if (categoryId == null || categoryId.isEmpty()) {
            req.setAttribute("error", "Please select a valid category.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
    

        /*  Fetch the category based on the category ID */
        CategoryRepository categoryRepository = new CategoryRepository(Category.class);
       
        System.out.println("CategoryRepo is created");

        Category category = categoryRepository.findById(Long.parseLong(categoryId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        
        System.out.println(category);

        
        Product product = new Product(name, price, "Added by Admin", quantity, category, LocalDateTime.now(), LocalDateTime.now());

    

        if (productId == null || productId.isEmpty()) {
            productService.createProduct(product);
        } else {
            product.setId(Long.parseLong(productId));
            productService.updateProduct(product);
        }
    
        /* Log the action and redirect  */
        System.out.println("Product saved: " + product);
        resp.sendRedirect("ProductController");
    }
}

