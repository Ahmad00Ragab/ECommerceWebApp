package gov.iti.jets.controllers;

import gov.iti.jets.models.Product;
import gov.iti.jets.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "productDetails", urlPatterns = "/details")
public class ProductDetailsController extends HttpServlet {
    private final ProductService productService ;

    public ProductDetailsController() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        // Fetch the product details using productId from the database (e.g., ProductDao)
        Product product = productService.findProductById2(Long.parseLong(productId));

        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product_details.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
