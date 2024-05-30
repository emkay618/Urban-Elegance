package com.moshao.dao;

import com.moshao.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection con;
    // Constructor with database connection parameter
    public UserDao(Connection con) {
        this.con = con;
    }
    // Empty constructor
    public UserDao() {
    }
    // Method for user login
    public User userLogin(String email, String password) {
        User user = null;
        String query = "SELECT * FROM Users WHERE email=? AND password=?";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Create a new User object and populate its fields
                    user = new User();
                    user.setUserID(rs.getInt("userID"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    // Method to register a new user
    public boolean registerUser(User user) {
        String query = "INSERT INTO Users (username, password, email, phone, country, city, postalCode, street, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getCountry());
            pst.setString(6, user.getCity());
            pst.setString(7, user.getPostalCode());
            pst.setString(8, user.getStreet());
            pst.setBoolean(9, user.isAdmin());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try ( PreparedStatement pst = con.prepareStatement(query); 
            ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // Create a new User object for each retrieved row and populate its fields
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setCountry(rs.getString("Country"));
                user.setCity(rs.getString("City"));
                user.setPostalCode(rs.getString("postalCode"));
                user.setStreet(rs.getString("street"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
     // Method to update user details
    public boolean updateUser(User user) {
        boolean updated = false;
        String query = "UPDATE Users SET username=?, email=?, phone=?, country=?, city=?, postalCode=?, street=?, isAdmin=? WHERE userID=?";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPhone());
            pst.setString(4, user.getCountry());
            pst.setString(5, user.getCity());
            pst.setString(6, user.getPostalCode());
            pst.setString(7, user.getStreet());
            pst.setBoolean(8, user.isAdmin());
            pst.setInt(9, user.getUserID());

            int rowsAffected = pst.executeUpdate();
            updated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

     // Method to retrieve all customers
    public List<User> getCustomers() {
        List<User> customers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users WHERE isAdmin = 0";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Create a new User object and populate its fields
                User customer = new User();
                customer.setUserID(rs.getInt("userID"));
                customer.setUsername(rs.getString("username"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    //delete user method to delete users
    public void deleteUser(String userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userID = ?";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, userId);
            pst.executeUpdate();
        }
    }

}
