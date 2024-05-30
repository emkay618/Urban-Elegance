package com.moshao.dao;

import com.moshao.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private Connection con;
    // Constructor with database connection parameter

    public AdminDao(Connection con) {
        this.con = con;
    }

    // Default constructor
    public AdminDao() {
    }

    // Method to authenticate a user
    public User userLogin(String email, String password) {
        User user = null;
        String query = "SELECT * FROM Users WHERE email=? AND password=?";
        try ( PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // If user exists, create a User object with retrieved data
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
        try ( PreparedStatement pst = con.prepareStatement(query);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // Create a User object for each retrieved row and add it to the list
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setCountry(rs.getString("country"));
                user.setCity(rs.getString("city"));
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

    // Method to retrieve only customers from the database
    public List<User> getCustomers() {
        List<User> customers = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE isAdmin = 0";
        try ( PreparedStatement pst = con.prepareStatement(query);  ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // Create a User object for each retrieved customer row and add it to the list
                User customer = new User();
                customer.setUserID(rs.getInt("userID"));
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setCountry(rs.getString("country"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("postalCode"));
                customer.setStreet(rs.getString("street"));
                customer.setAdmin(rs.getBoolean("isAdmin"));

                // Debug statements
                System.out.println("Retrieved Customer: " + customer.getUsername());

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
