package gov.iti.jets.controllers;

import com.google.gson.Gson;

import gov.iti.jets.repositories.CategoryRepository;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.system.persistence.CreateEntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import gov.iti.jets.models.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDateTime;



/* ======================================================================================== */
/*    This is Servlet Enables the Admin to View, Add, Edit and Delete Products              */
/* ======================================================================================== */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Validate session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            // Redirect to login if no session
            req.setAttribute("errorMessage", "Please log in to access this page.");
            req.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(req, resp);
            return;
        }

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("ProductController");
        } else if ("edit".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = productService.findProductById(id);

            if (product.isPresent()) {
                req.setAttribute("product", product.get());

                // Fetch and set categories
                CategoryService categoryService = new CategoryService();
                Set<Category> categories = categoryService.findAllCategories();
                req.setAttribute("categories", categories);

                req.getRequestDispatcher("WEB-INF/views/admin/edit-product.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("ProductController");
            }
        }
        else if("create".equals(action)){
             // Fetch and set categories
             CategoryService categoryService = new CategoryService();
             Set<Category> categories = categoryService.findAllCategories();
             req.setAttribute("categories", categories);
             
             req.getRequestDispatcher("WEB-INF/views/admin/add-product.jsp").forward(req, resp);
        } 
        else {
            // Default action: show product list
            Set<Product> products = productService.findAllProducts();
            req.setAttribute("productList", products);

            // Fetch and set categories
            CategoryService categoryService = new CategoryService();
            Set<Category> categories = categoryService.findAllCategories();
            req.setAttribute("categories", categories);

            req.getRequestDispatcher("WEB-INF/views/admin/admin-panel.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Validate session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            // Redirect to login if no session
            req.setAttribute("errorMessage", "Please log in to access this page.");
            req.getRequestDispatcher("/WEB-INF/views/admin/admin-login.jsp").forward(req, resp);
            return;
        }

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("ProductController");
        } else {
            // Handle product creation and update
            handleCreateOrUpdate(req, resp);
        }
    }

    private void handleCreateOrUpdate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String name = req.getParameter("name");
        String imageUrl = req.getParameter("imageUrl"); // Capture the image URL
        String description = req.getParameter("description"); // Capture the image URL
        String shoeSize = req.getParameter("size"); // Capture the size
        String shoeColor = req.getParameter("color"); // Capture the color 
        String brand = req.getParameter("brand"); // Capture the brand
        BigDecimal price;
        int quantity;

        try {
            price = new BigDecimal(req.getParameter("price"));
            quantity = Integer.parseInt(req.getParameter("quantity"));
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format for price or quantity.");
            req.getRequestDispatcher("WEB-INF/views/admin/admin-panel.jsp").forward(req, resp);
            return;
        }


        String categoryId = req.getParameter("category");
        if (categoryId == null || categoryId.isEmpty()) {
            req.setAttribute("error", "Please select a valid category.");
            req.getRequestDispatcher("WEB-INF/views/admin/admin-panel.jsp").forward(req, resp);
            return;
        }

        CategoryRepository categoryRepository = new CategoryRepository();
        Category category = categoryRepository.findById(Long.parseLong(categoryId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        // Create or update the product with the imageUrl
        Product product = new Product(name, price, description, quantity, category, LocalDateTime.now(),
                LocalDateTime.now());
        product.setImageUrl(imageUrl); // Set the image URL
        product.setShoeSize(shoeSize); // Set the shoeSize
        product.setShoeColor(shoeColor); // Set the shoeColor
        product.setBrand(brand); // Set the Brand

        if (productId == null || productId.isEmpty()) {
            productService.createProduct(product);
        } else {
            product.setId(Long.parseLong(productId));
            productService.updateProduct(product);
        }

        resp.sendRedirect("ProductController");
    }

}
