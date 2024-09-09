package gov.iti.jets.controllers;

import gov.iti.jets.dtos.ProductDto;
import gov.iti.jets.services.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "homeServlet", urlPatterns ={"/home", "/products"} )
public class HomeController extends HttpServlet {
    private ProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String category = req.getParameter("category");
          if(category==null || category.isBlank() || category.isEmpty())
          handleProducts(req,resp);
          else handleProductsByCategory(req,resp,category);
    }


    private  void handleProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProductDto> homeProducts=productService.findAllProductsUsingDTO();
        homeProducts.forEach(System.out::println);
        req.setAttribute("homeProducts", homeProducts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req,resp);
    }

    private void handleProductsByCategory(HttpServletRequest req, HttpServletResponse resp, String category) throws ServletException, IOException {
        Set<ProductDto> homeProductsByCategory=productService.findProductsByCategoryUsingProductDTO(category);
        req.setAttribute("homeProducts", homeProductsByCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req,resp);

    }

}
