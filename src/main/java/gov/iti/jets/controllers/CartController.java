    package gov.iti.jets.controllers;

    import gov.iti.jets.models.CartItem;
    import gov.iti.jets.services.CartService;
    import gov.iti.jets.services.ProductService;
    import gov.iti.jets.services.UserService;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import java.io.IOException;
    import java.math.BigDecimal;
    import java.util.Set;

    @WebServlet(urlPatterns = "/cart")
    public class CartController extends HttpServlet {

        private CartService cartService;
        private UserService userService;
        private ProductService productService;

        @Override
        public void init() throws ServletException {
            cartService = new CartService();
            userService = new UserService();
            productService = new ProductService();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long userId = (Long)request.getSession().getAttribute("userId");

            if (userId == null) {
                request.setAttribute("error", "User not logged in");
                request.getRequestDispatcher("/login").include(request, response);
                return;
            }

            String action = request.getParameter("action");
            if (action == null) action = "list";
            switch (action) {
                case "listByUser":
                    listCartItemsByUser(request, response);
                    break;
                case "checkCart":
                    checkCart(request, response);
                    break;
                case "add":
                    saveCartItem(request, response);
                    break;
                case "update":
                    updateCartItem(request, response);
                    break;
                case "delete":
                    deleteCartItem(request, response);
                    break;
                case "clear":
                    clearCart(request, response);
                    break;
                default:
                    request.setAttribute("error", "Invalid action");
                    request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doGet(request, response);
        }

        private void checkCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }

        private void listCartItemsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long userId = (Long) request.getSession().getAttribute("userId");

            // Get cart items
            Set<CartItem> cartItems = cartService.findCartByUserId(userId);
            request.setAttribute("cartItems", cartItems);

            // Calculate total price
            BigDecimal totalPrice = cartItems.stream()
                    .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            request.setAttribute("totalPrice", totalPrice);

            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }

        private void saveCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long userId = (Long) request.getSession().getAttribute("userId");
            int quantity = 1;

            cartService.addProductToCart(userId, productId, quantity);
            response.sendRedirect("cart?action=listByUser");
        }

        private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long userId = (Long) request.getSession().getAttribute("userId");

            cartService.updateQuantity(userId, productId, quantity);
            response.sendRedirect("cart?action=listByUser");
        }

        private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long userId = (Long) request.getSession().getAttribute("userId");
            Long productId = Long.parseLong(request.getParameter("productId"));
            cartService.removeItem(userId, productId);
            response.sendRedirect("cart?action=listByUser");
        }

        private void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long userId = (Long) request.getSession().getAttribute("userId");
            cartService.clearCart(userId);
            response.sendRedirect("cart?action=listByUser");
        }
    }