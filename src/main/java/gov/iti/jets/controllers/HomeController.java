package gov.iti.jets.controllers;

import gov.iti.jets.dtos.ProductDto;
import gov.iti.jets.models.Category;
import gov.iti.jets.services.CategoryService;
import gov.iti.jets.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@WebServlet(name = "homeServlet", urlPatterns = {"/home", "/products"})
public class HomeController extends HttpServlet {
    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController() {
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        String shoeColor = req.getParameter("color");
        String shoeSize = req.getParameter("size");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String searchProduct = req.getParameter("searchShoes");
        if (searchProduct != null && !searchProduct.isEmpty() && !searchProduct.isBlank()) {
            handleSearch(req, resp, searchProduct);
        } else
            handleFiltration(req, resp, category, shoeSize, shoeColor, productService.parseBigDecimal(minPrice), productService.parseBigDecimal(maxPrice));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void handleFiltration(HttpServletRequest request, HttpServletResponse response, String category,
                                 String size, String color, BigDecimal minPrice, BigDecimal maxPrice) throws ServletException, IOException {
        try {
            displayAllCategories(request, response);

            String pageNumberParam = request.getParameter("pageNumber");
            int pageNumber = (pageNumberParam == null || pageNumberParam.isEmpty()) ? 1 : Integer.parseInt(pageNumberParam);
            int pageSize = 10;

            Set<ProductDto> homeProducts = null;

            if ("ALL".equals(category)) {
                homeProducts = productService.filterProducts(null, null, null, minPrice, maxPrice, pageNumber, pageSize);
            } else {
                homeProducts = productService.filterProducts(category, size, color, minPrice, maxPrice, pageNumber, pageSize);
            }

            request.setAttribute("homeProducts", homeProducts);

            int totalProducts = productService.countFilteredProducts(category, size, color, minPrice, maxPrice);
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid page number format", e);
        } catch (Exception e) {
            throw new ServletException("Error while filtering products", e);
        }
    }
    public void handleSearch(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
        try {
            displayAllCategories(request, response);

            String pageNumberParam = request.getParameter("pageNumber");
            int pageNumber = (pageNumberParam == null || pageNumberParam.isEmpty()) ? 1 : Integer.parseInt(pageNumberParam);
            int pageSize = 10;

            Set<ProductDto> homeProducts = productService.findProductByNameUsingProductDTO(name, pageNumber, pageSize);
            request.setAttribute("homeProducts", homeProducts);

            int totalProducts = productService.countProductByName(name);
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            request.setAttribute("currentPage", pageNumber);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid page number format", e);
        } catch (Exception e) {
            throw new ServletException("Error while filtering products", e);
        }
    }



    private void displayAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           Set<Category> categories = categoryService.findAllCategories();
           request.setAttribute("categories", categories);
       }catch (NumberFormatException e) {
           throw new ServletException("Invalid page number format", e);
       } catch (Exception e) {
           throw new ServletException("Error while filtering products", e);
       }
    }







}
