package gov.iti.jets.controllers;

import com.google.gson.Gson;

import gov.iti.jets.repositories.CategoryRepository;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.ProductService;
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



/* ======================================================================================== */
/*    This is Servlet Enables the Admin to View, Add, Edit and Delete Products              */
/* ======================================================================================== */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Handle product deletion via GET
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("ProductController");
        } else if ("edit".equals(action)) {
            // Handle edit product
            long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = productService.findProductById(id);

            if (product.isPresent()) {
                req.setAttribute("product", product.get());

                /* Fetch and set categories */
                CategoryService categoryService = new CategoryService();
                Set<Category> categories = categoryService.findAllCategories();
                req.setAttribute("categories", categories);

                req.getRequestDispatcher("WEB-INF/views/admin/edit-product.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("ProductController");
            }
        } else {
            // Default action: show product list
            Set<Product> products = productService.findAllProducts();
            req.setAttribute("productList", products);

            /* Fetch and set categories */
            CategoryService categoryService = new CategoryService();
            Set<Category> categories = categoryService.findAllCategories();
            req.setAttribute("categories", categories);

            req.getRequestDispatcher("WEB-INF/views/admin/admin-panel.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Handle product deletion via POST
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("ProductController");
        } else {
            // Handle product creation and update (same as before)
            handleCreateOrUpdate(req, resp);
        }
    }

    private void handleCreateOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String name = req.getParameter("name");
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


        Product product = new Product(name, price, "Added by Admin", quantity, category, LocalDateTime.now(), LocalDateTime.now());


        if (productId == null || productId.isEmpty()) {
            productService.createProduct(product);
        } else {
            product.setId(Long.parseLong(productId));
            productService.updateProduct(product);
        }

        resp.sendRedirect("ProductController");
    }
}