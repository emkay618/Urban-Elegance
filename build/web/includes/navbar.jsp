<%@page import="com.moshao.model.User"%>
<% User authenticate = (User) session.getAttribute("auth"); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Navigation bar</title>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet"/>
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <style>
        .nav-link:hover,
        select:hover,
        input[type="number"]:hover {
            border: 1px solid #f60;
            border-radius: 9999px;
            cursor: pointer;
            min-width: 50px;
        }

        .btn-outline-success {
            background-color: #f60;
            border-radius: 9999px;
            color: #fff!important;
            cursor: pointer;
        }

        .currency-container {
            position: relative;
        }

        #currency-form {
            display: none;
            position: absolute;
            top: 40px; /* Adjust as needed */
            left: 50%;
            transform: translateX(-50%);
            width: 300px; /* Set the width as needed */
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            z-index: 999; /* Adjust the value as needed */
        }
        .navbar {
            transition: top 0.3s; /* Add smooth transition effect when navbar hides/shows */
        }
        .fixed-top {
            position: fixed;
            width: 100%;
            top: 0;
            z-index: 1000; /* Ensure the navbars are above other content */
        }

    </style>
</head>
<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <span class="navbar-brand font-weight-bold text-danger display-4">UrbanElegance</span>
        <div class="container d-flex justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link rounded-pill border" href="searchengine.jsp">Click To Search Items By Typing</a>
                </li>
            </ul>
        </div>
    </nav>

    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" style="margin-top: 56px;">
        <div class="container">
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!-- Middle section with search and filters -->
                <% if (authenticate == null || !authenticate.isAdmin()) { %>
                <form class="form-inline my-2 my-lg-0 mx-auto" action="filter-products" method="get">
                    <select name="sizeFilter" id="sizeFilter" class="form-control">
                        <option value="">Select Size</option>
                        <option value="20">20</option>
                        <option value="22">22</option>
                        <option value="26">26</option>
                        <option value="28">28</option>
                        <option value="30">30</option>
                        <option value="32">32</option>
                        <option value="34">34</option>
                        <option value="36">36</option>
                    </select>
                    <select name="colorFilter" id="colorFilter" class="form-control">
                        <option value="">Select Color</option>
                        <option value="blue">Blue</option>
                        <option value="black">Black</option>
                        <option value="yellow">Yellow</option>
                        <option value="white">White</option>
                        <option value="green">Green</option>
                        <option value="gray">Gray</option>
                    </select>
                    <select class="custom-select mr-sm-2" name="type" style="width: 140px;">
                        <option value="" selected>Filter By Type</option>
                        <option value="shirts">Shirts</option>
                        <option value="pants">Pants</option>
                        <option value="suits">Suits</option>
                        <option value="hats">Hats</option>
                        <option value="socks">Socks</option>
                        <option value="belts">Belts</option>
                        <option value="jewellery">Jewelry</option>
                        <option value="shoes">Shoes</option>
                        <option value="luxury fragrance">Luxury Fragrance</option>
                    </select>
                    <input class="form-control mr-sm-2" type="number" placeholder="Min price" name="minPrice" style="width: 106px;" min="0">
                    <input class="form-control mr-sm-2" type="number" placeholder="Max price" name="maxPrice" style="width: 109px;" min="0">
                    <button class="btn btn-outline-success btn-sm my-2 my-sm-0" type="submit">Search</button>
                </form>

                <div class="currency-container">
                    <button class="btn btn-outline-success btn-sm my-2 my-sm-0 btn-currency">Currency</button>
                    <div id="currency-form" style="display: none;">
                        <!-- Currency Converter Form -->
                        <label for="amount">Amount:</label>
                        <input type="number" id="amount" value="100" />
                        <div class="dropdowns">
                            <select id="from-currency-select"></select>
                            <select id="to-currency-select"></select>
                        </div>
                        <button id="convert-button">Convert</button>
                        <p id="result"></p>
                    </div>
                </div>

                <% } %>
                <!-- Right side navigation -->
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>Cart <span class="badge badge-danger">${cart_list.size()}</span></a></li>
                            <% if (((User) authenticate) != null) { %>
                    <li class="nav-item"><a class="nav-link" href="orderServlet"><i class="fas fa-clipboard-list"></i> Orders</a></li>
                    <li class="nav-item"><a class="nav-link" href="log-out"><i class="fas fa-sign-out-alt"></i>Logout</a></li>
                        <% if (((User) authenticate).isAdmin()) { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="productManagementDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-cogs"></i>Product Management
                        </a>
                        <div class="dropdown-menu" aria-labelledby="productManagementDropdown">
                            <a class="dropdown-item" href="productAdd.jsp">Add Product</a>
                            <a class="dropdown-item" href="productUpdate.jsp">Update Product</a>
                            <a class="dropdown-item" href="viewProductLevel.jsp">Stock Level</a>
                            <a class="dropdown-item" href="productDelete.jsp">Delete Product</a>
                            <a class="dropdown-item" href="productRetire.jsp">Retire Product</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="orderManagementDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-users"></i>User Management
                        </a>
                        <div class="dropdown-menu" aria-labelledby="orderManagementDropdown">
                            <a class="dropdown-item" href="addUser.jsp">Create User</a>
                            <a class="dropdown-item" href="viewUsersServlet">View User</a>
                            <a class="dropdown-item" href="updateUserServlet">Update User</a>
                            <a class="dropdown-item" href="deleteUserServlet">Delete User</a>
                        </div>
                    </li>
                    <% } %>
                    <% } else { %> 
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                        <% }%>
                </ul>

            </div>
        </div>
    </nav>
    <!-- Script With Array Of Supported Country Codes -->
    <script src="currency-codes.js"></script>
    <!-- Script with API Key -->
    <script src="api-key.js"></script>
    <!-- Script -->
    <script src="script.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // Initialize a variable to track whether the form is open
            let isFormOpen = false;

            const currencyButton = document.querySelector(".btn-currency");
            const currencyForm = document.querySelector("#currency-form");

            function showForm() {
                // Only show the form if it's not already open
                if (!isFormOpen) {
                    currencyForm.style.display = "block";
                    isFormOpen = true;
                }
            }

            function hideForm() {
                // Only hide the form if it's open
                if (isFormOpen) {
                    currencyForm.style.display = "none";
                    isFormOpen = false;
                }
            }

            // Show form when mouse enters button or form
            currencyButton.addEventListener("mouseenter", showForm);
            currencyForm.addEventListener("mouseenter", showForm);

            // Hide form when mouse leaves button or form
            currencyButton.addEventListener("mouseleave", hideForm);
            currencyForm.addEventListener("mouseleave", hideForm);

            // Keep form open when interacting with its elements
            currencyForm.addEventListener("mouseenter", showForm);
            currencyForm.addEventListener("mouseleave", showForm);
        });
    </script>
</body>
</html>
