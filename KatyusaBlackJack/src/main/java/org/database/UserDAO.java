package org.database;

import org.models.User;
import java.sql.*;
import java.security.MessageDigest;

public class UserDAO {
    public boolean register(User user) {
        String sqlInsertUser = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        String sqlInsertTokens = "INSERT INTO user_tokens (id) VALUES (?)";

        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(3, user.getEmail());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return false;

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                PreparedStatement tokenPs = conn.prepareStatement(sqlInsertTokens);
                tokenPs.setInt(1, userId);
                tokenPs.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String hashedInput = hashPassword(password);

                if (storedPassword.equals(hashedInput)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(storedPassword);
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
