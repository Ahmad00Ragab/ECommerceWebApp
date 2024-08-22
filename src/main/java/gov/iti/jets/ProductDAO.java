// package gov.iti.jets;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.util.ArrayList;
// import java.util.List;

// public class ProductDAO {
//     // Method to get products by category ID
//     public static List<Product> getProductsByCategoryId(int categoryId) {
//         List<Product> products = new ArrayList<>();
//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE categoryId = ?")) {
//             stmt.setInt(1, categoryId);
//             ResultSet rs = stmt.executeQuery();
//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 String name = rs.getString("name");
//                 double price = rs.getDouble("price");
//                 int quantity = rs.getInt("quantity");
//                 String description = rs.getString("description");
//                 products.add(new Product(id, name, price, categoryId, quantity, description));
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return products;
//     }
// }
