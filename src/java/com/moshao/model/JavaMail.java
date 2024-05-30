package com.moshao.model;

public class JavaMail {
    public static void main(String[] args) throws Exception {
        
        String invoiceContent = "<table border=\"1\">"
                + "<tr><th>Order ID</th><th>Product Name</th><th>Quantity</th><th>Total Price</th></tr>"
                + "<tr><td>12345</td><td>Example Product</td><td>1</td><td>$50</td></tr>"
                + "</table>";
        
        // Specify the subject of the email
        String subject = "Invoice for Your Order";

        // Specify the message of the email
        String message = "Dear Customer,<br><br>Thank you for your order. Please find attached the invoice details.";

        // Specify the recipient's email address
        String recipientEmail = "emkay618@gmail.com";

        // Send the email with the HTML-formatted invoice content
        JavaMailUtil.sendHtmlMail(recipientEmail, subject, message, invoiceContent);
    }
}
