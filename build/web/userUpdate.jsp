<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="com.moshao.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // List of countries
    List<String> countries = Arrays.asList("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Palestinian Territories", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/head.jsp" %>
        <title>UrbanElegance</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/includes/navbar.jsp" %>

        <div class="container">
            <%
                try {
                    // Retrieve data from the servlet request attributes
                    List<User> users = (List<User>) request.getAttribute("users");

                    if (users != null && !users.isEmpty()) {
            %>
            <div class="card-header my-3">All Users</div>
            <div class="row">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Country</th>
                            <th>City</th>
                            <th>Postal Code</th>
                            <th>Street</th>
                            <th>User Type</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterate over each user and display details -->
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.userID}</td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>${user.phone}</td>
                                <td>${user.country}</td>
                                <td>${user.city}</td>
                                <td>${user.postalCode}</td>
                                <td>${user.street}</td>
                                <td>${user.isAdmin ? 'Admin' : 'Customer'}</td>
                                <td>
                                    <!-- Edit button with onclick event to populate form fields -->
                                    <button type="button" class="btn btn-primary" onclick="showUpdateForm(
                                                '${user.userID}',
                                                '${user.username}',
                                                '${user.email}',
                                                '${user.phone}',
                                                '${user.country}',
                                                '${user.city}',
                                                '${user.postalCode}',
                                                '${user.street}',
                                                '${user.isAdmin}')">Edit</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <% } else { %>
            <div class="alert alert-info" role="alert">
                No Users Found
            </div>
            <% } %>
            <% } catch (Exception e) {
                    e.printStackTrace();
                    out.println("Error occurred while rendering users: " + e.getMessage());
                } %>
        </div>

        <!-- Update Form Modal -->
        <div id="updateForm" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Update User</h4>
                    </div>
                    <div class="modal-body">
                        <form id="updateUserForm" action="updateUserServlet" method="post">
                            <input type="hidden" id="userId" name="userId">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone number">
                            </div>
                            <div class="form-group">
                                <label for="country">Country</label>
                                <select class="form-control" id="country" name="country">
                                    <% for (String country : countries) {%>
                                    <option value="<%= country%>"><%= country%></option>
                                    <% }%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="city">City</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="Enter city">
                            </div>
                            <div class="form-group">
                                <label for="postalCode">Postal Code</label>
                                <input type="text" class="form-control" id="postalCode" name="postalCode" placeholder="Enter postal code">
                            </div>
                            <div class="form-group">
                                <label for="street">Street</label>
                                <input type="text" class="form-control" id="street" name="street" placeholder="Enter street">
                            </div>
                            <div class="form-group">
                                <label for="isAdmin">User Type</label>
                                <select class="form-control" id="isAdmin" name="isAdmin">
                                    <option value="true">Admin</option>
                                    <option value="false">Customer</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/includes/footer.jsp" %>

        <!-- Script to populate the update form fields -->
        <script>
            let formCount = 0; // Declare formCount globally

            // Function to display form that updates users
            function showUpdateForm(userId, username, email, phone, country, city, postalCode, street, isAdmin) {
                // Increment the form count to generate unique ids
                formCount++;

                // Populate the update form fields with the user details
                document.getElementById('userId').value = userId;
                document.getElementById('username').value = username;
                document.getElementById('email').value = email;
                document.getElementById('phone').value = phone;
                document.getElementById('country').value = country;
                document.getElementById('city').value = city;
                document.getElementById('postalCode').value = postalCode;
                document.getElementById('street').value = street;
                document.getElementById('isAdmin').value = isAdmin ? "true" : "false";

                // Show the modal
                $('#updateForm').modal('show');
            }
        </script>
        
    </body>
</html>
