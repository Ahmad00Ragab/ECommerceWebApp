// package gov.iti.jets;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class CategoryDAO {

//     // Method to get a category by ID
//     public static Category getCategoryById(int id) {
//         Category category = null;
//         String query = "SELECT * FROM categories WHERE id = ?";

//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setInt(1, id);
//             ResultSet rs = stmt.executeQuery();

//             if (rs.next()) {
//                 int categoryId = rs.getInt("id");
//                 String name = rs.getString("name");
//                 String description = rs.getString("description");
//                 Integer parentCategoryId = rs.getObject("parentCategoryId", Integer.class);

//                 category = new Category(categoryId, name, description, parentCategoryId);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return category;
//     }

//     // Method to get all categories
//     public static List<Category> getAllCategories() {
//         List<Category> categories = new ArrayList<>();
//         String query = "SELECT * FROM categories";

//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query);
//              ResultSet rs = stmt.executeQuery()) {

//             while (rs.next()) {
//                 int id = rs.getInt("id");
//                 String name = rs.getString("name");
//                 String description = rs.getString("description");
//                 Integer parentCategoryId = rs.getObject("parentCategoryId", Integer.class);

//                 categories.add(new Category(id, name, description, parentCategoryId));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return categories;
//     }

//     // Method to add a new category
//     public static void addCategory(Category category) {
//         String query = "INSERT INTO categories (name, description, parentCategoryId) VALUES (?, ?, ?)";

//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setString(1, category.getName());
//             stmt.setString(2, category.getDescription());
//             stmt.setObject(3, category.getParentCategoryId(), java.sql.Types.INTEGER);

//             stmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to update an existing category
//     public static void updateCategory(Category category) {
//         String query = "UPDATE categories SET name = ?, description = ?, parentCategoryId = ? WHERE id = ?";

//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setString(1, category.getName());
//             stmt.setString(2, category.getDescription());
//             stmt.setObject(3, category.getParentCategoryId(), java.sql.Types.INTEGER);
//             stmt.setInt(4, category.getId());

//             stmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to delete a category by ID
//     public static void deleteCategory(int id) {
//         String query = "DELETE FROM categories WHERE id = ?";

//         try (Connection conn = DatabaseConnection.getConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setInt(1, id);

//             stmt.executeUpdate();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
// }
