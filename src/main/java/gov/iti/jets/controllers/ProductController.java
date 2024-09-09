package gov.iti.jets.controllers;

import com.google.gson.Gson;
import gov.iti.jets.models.Product;
import gov.iti.jets.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.*;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            productService.deleteProduct(id);
            resp.sendRedirect("index.jsp"); 
        } else if ("fetch".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            Optional<Product> product = productService.getProductById(id);

            Gson gson = new Gson();
            String productJson = gson.toJson(product.orElse(null)); // Use Optional
            resp.setContentType("application/json");
            resp.getWriter().write(productJson);
        } else {
            Set<Product> products = productService.getAllProducts();
            req.setAttribute("productList", products);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String categoryId = req.getParameter("category");

        Product product = new Product(name, price, quantity, categoryId);

        if (productId == null || productId.isEmpty()) {
            productService.createProduct(product);
        } else {
            product.setId(Long.parseLong(productId));
            productService.updateProduct(product);
        }

        resp.sendRedirect("index.jsp");
    }
}
