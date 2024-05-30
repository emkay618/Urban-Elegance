package com.moshao.servlet;

import com.moshao.connection.DbCon;
import com.moshao.dao.ProductDao;
import com.moshao.model.Product;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String size = request.getParameter("size");
        String color = request.getParameter("color");

        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        // Save the image file to a directory on the server
        String uploadPath = "C:\\Users\\Simon Pieter\\OneDrive\\Documentos\\NetBeansProjects\\JAVA END\\2128084_EndAssessment\\web\\product-images" + File.separator + fileName;
        Files.copy(fileContent, Paths.get(uploadPath), StandardCopyOption.REPLACE_EXISTING);

        // Set the imageUrl to the file name
        String imageUrl = "product-images/" + fileName;

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImageUrl(imageUrl);
        product.setSize(size);
        product.setColor(color);

        try {
            // Get the real path to the SQL file
            String realPath = request.getServletContext().getRealPath("/exportedDbFile/urbanelegance2128084.sql");
            // Create a ProductDao object with the connection
            ProductDao productDao = new ProductDao(DbCon.getConnection(realPath));
            if (productDao.addProduct(product)) {
                response.sendRedirect("index.jsp"); // Redirect to product page after adding product
            } else {
                response.getWriter().println("Failed to add product. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            response.getWriter().println("Failed to add product. Please try again.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
