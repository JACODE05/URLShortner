import java.sql.*;

public class database {
    private static final String URL = "jdbc:sqlite:urlshortener.db";

    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS urls (" +
                "short_code TEXT PRIMARY KEY," +
                "long_url TEXT NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save(String shortCode, String longUrl) {
        String sql = "INSERT INTO urls (short_code, long_url) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, shortCode);
            pstmt.setString(2, longUrl);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String findLongUrl(String shortCode) {
        String sql = "SELECT long_url FROM urls WHERE short_code = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, shortCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("long_url");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exists(String shortCode) {
        String sql = "SELECT 1 FROM urls WHERE short_code = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, shortCode);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean delete(String shortCode) {
        String sql = "DELETE FROM urls WHERE short_code = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, shortCode);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
