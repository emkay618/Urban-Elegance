package com.moshao.dao;

import com.moshao.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    // Constructor with database connection parameter

    public OrderDao(Connection con) {
        this.con = con;
    }

    // Method to insert data to orders
    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            // Prepare and execute the insert query
            query = "INSERT INTO orders (userID, productID, orderQuantity, orderDate) VALUES(?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, model.getUserid());
            pst.setInt(2, model.getProductid());
            pst.setInt(3, model.getQuantity());
            pst.setTimestamp(4, model.getOrderDate());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
        } finally {
            closeResources();
        }
        return result;
    }

    // Method to retrieve orders for a specific user
    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            // Prepare and execute the query to retrieve user orders
            query = "SELECT o.orderID, o.userID, o.productID, o.orderQuantity, o.orderDate, p.price "
                    + "FROM orders o "
                    + "INNER JOIN products p ON o.productID = p.productID "
                    + "WHERE o.userID=? ORDER BY o.orderID DESC";

            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                // Create Order objects for each retrieved row and add them to the list
                Order order = new Order();
                order.setOrderId(rs.getInt("orderID"));
                order.setUserid(rs.getInt("userID"));
                order.setProductid(rs.getInt("productID"));
                order.setQuantity(rs.getInt("orderQuantity"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setPrice(rs.getDouble("price"));
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user orders: " + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }

    // Method to cancel an order
    public boolean cancelOrder(int id) {
        boolean result = false;
        try {
            // Prepare and execute the query to delete the order
            query = "DELETE FROM orders WHERE orderID=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("Error cancelling order: " + e.getMessage());
        } finally {
            closeResources();
        }
        return result;
    }

    // Add method to retrieve all orders for admins
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            // Prepare and execute the query to retrieve all orders
            query = "SELECT * FROM orders ORDER BY orderID DESC";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                // Create Order objects for each retrieved row and add them to the list
                Order order = new Order();
                order.setOrderId(rs.getInt("orderID"));
                order.setUserid(rs.getInt("userID"));
                order.setProductid(rs.getInt("productID"));
                order.setQuantity(rs.getInt("orderQuantity"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                list.add(order);
            }
            System.out.println("Total orders retrieved: " + list.size()); // Debugging statement
        } catch (SQLException e) {
            System.out.println("Error retrieving all orders: " + e.getMessage());
        } finally {
            closeResources();
        }
        return list;
    }

    // Method to retrieve orders for a specific user
    public List<Order> getUserOrders(int userId) {
        List<Order> userOrders = new ArrayList<>();
        try {
            // Prepare and execute the query to retrieve orders for a specific user
            query = "SELECT * FROM orders WHERE userID=? ORDER BY orderID DESC";
            pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                // Create Order objects for each retrieved row and add them to the list
                Order order = new Order();
                order.setOrderId(rs.getInt("orderID"));
                order.setUserid(rs.getInt("userID"));
                order.setProductid(rs.getInt("productID"));
                order.setQuantity(rs.getInt("orderQuantity"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                userOrders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user orders: " + e.getMessage());
        } finally {
            closeResources();
        }
        return userOrders;
    }

    // Method to retrieve order details based on order ID
    public List<Order> getOrderDetails(int orderId) {
        List<Order> orderDetails = new ArrayList<>();
        try {
            // Prepare and execute the query to retrieve order details based on order ID
            query = "SELECT * FROM orders WHERE orderID=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            rs = pst.executeQuery();
            while (rs.next()) {
                // Create Order objects for each retrieved row and add them to the list
                Order order = new Order();
                order.setOrderId(rs.getInt("orderID"));
                order.setUserid(rs.getInt("userID"));
                order.setProductid(rs.getInt("productID"));
                order.setQuantity(rs.getInt("orderQuantity"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                // Assuming you have the customer's email address stored in the order details
                order.setCustomerEmail(rs.getString("customerEmail"));
                orderDetails.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving order details: " + e.getMessage());
        } finally {
            closeResources();
        }
        return orderDetails;
    }

    // Method to retrieve the latest order ID
    public int getLatestOrderId() {
        int latestOrderId = -1;
        try {
            // Prepare and execute the query to retrieve the maximum order ID
            String query = "SELECT MAX(orderID) AS latestOrderId FROM orders";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                latestOrderId = rs.getInt("latestOrderId");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latestOrderId;
    }

    // Method to close resources
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }

}
