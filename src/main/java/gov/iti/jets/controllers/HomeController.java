package gov.iti.jets.controllers;

import gov.iti.jets.dtos.ProductDto;
import gov.iti.jets.services.ProductService;
import gov.iti.jets.services.UserService;
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
          handleProducts(req,resp);
    }


    private  void handleProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProductDto> homeProducts=productService.findAllProductsUsingDTO();
        req.setAttribute("homeProducts", homeProducts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(req,resp);
    }

}
