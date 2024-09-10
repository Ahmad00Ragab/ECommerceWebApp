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
          String productName = req.getParameter("productName");
          if(category!=null && !category.isBlank() && !category.isEmpty())
          handleSearchByCategory(req,resp,category);
          else if (productName!=null && !productName.isBlank() && !productName.isEmpty() ) handleSearchForProducts(req,resp,productName);
          else  handleProducts(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public void handleProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNumberParam = request.getParameter("pageNumber");
        int pageNumber = (pageNumberParam == null || pageNumberParam.isEmpty()) ? 1 : Integer.parseInt(pageNumberParam);
        int pageSize = 3;

        Set<ProductDto> products = productService.findAllProductsUsingDTO(pageNumber, pageSize);

        request.setAttribute("homeProducts", products);

        int totalProducts = productService.countAllProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }




    public void handleSearchByCategory(HttpServletRequest request, HttpServletResponse response, String category) throws ServletException, IOException {
        String pageNumberParam = request.getParameter("pageNumber");
        int pageNumber = (pageNumberParam == null || pageNumberParam.isEmpty()) ? 1 : Integer.parseInt(pageNumberParam);
        int pageSize = 3;

        Set<ProductDto> products = productService.findProductsByCategoryUsingProductDTO(category, pageNumber, pageSize);

        request.setAttribute("homeProducts", products);


        int totalProducts = productService.countProductsByCategory(category);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);


        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }


    private  void handleSearchForProducts(HttpServletRequest req, HttpServletResponse resp, String name) throws ServletException, IOException {
        int pageNumber = req.getParameter("pageNumber") != null? Integer.parseInt(req.getParameter("pageNumber")) : 1;
        int pageSize = 3;
        Set<ProductDto> products = productService.findProductByNameUsingProductDTO(name, pageNumber, pageSize);

        int totalProducts = productService.countByName(name);
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        req.setAttribute("homeProducts", products);
        req.setAttribute("currentPage", pageNumber);
        req.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    /*private void handleSortProductsByCategoryAndPrice(HttpServletRequest req, HttpServletResponse resp, String category) throws ServletException, IOException {
        Set<ProductDto> productsByCategory=productService.findProductsByCategoryUsingProductDTO(category);
        req.setAttribute("homeProducts", productsByCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req,resp);
    }*/
}
