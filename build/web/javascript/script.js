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

//fuction to detele product
function deleteProduct(productId) {
    if (confirm("Are you sure you want to delete this product?")) {
        // Send an AJAX request to the servlet to delete the product
        fetch('delete-product?id=' + productId, {
            method: 'DELETE'
        })
                .then(response => {
                    if (response.ok) {
                        // Reload the page to reflect the changes after deletion
                        location.reload();
                    } else {
                        // Handle the error condition if deletion fails
                        console.error('Failed to delete product');
                    }
                })
                .catch(error => console.error('Error deleting product:', error));
    }
}
//For invoice
// For invoice
function generateInvoice(orderId, userId, productId, quantity, orderDate) {
    // Display the invoice container
    document.getElementById("invoiceContainer").style.display = "block";

    // Create the table structure
    var invoiceContent = `
        <table class="table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${orderId}</td>
                    <td>${userId}</td>
                    <td>${productId}</td>
                    <td>${quantity}</td>
                    <td>${orderDate}</td>
                </tr>
            </tbody>
        </table>
    `;

    // Set the innerHTML of the invoice content div to the table
    document.getElementById("invoiceContent").innerHTML = invoiceContent;
}


function closeInvoice() {
    // Hide the invoice container
    document.getElementById("invoiceContainer").style.display = "none";
}

function sendInvoice() {

    alert("Invoice sent successfully!");

    closeInvoice();
}
function getUserOrders() {
    var userId = document.getElementById("userSelect").value; // Get the selected user ID

    // Send an AJAX request to fetch the selected user's orders
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "getUserOrders?userId=" + userId, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Parse the response JSON data
                var orders = JSON.parse(xhr.responseText);
                // Update the orders table with the fetched orders
                updateOrdersTable(orders);
            } else {
                console.error("Error fetching user orders: " + xhr.statusText);
            }
        }
    };
    xhr.send();
}

function updateOrdersTable(orders) {
    var ordersTableBody = document.getElementById("ordersTable");
    // Clear existing table rows
    ordersTableBody.innerHTML = "";
    // Iterate over the fetched orders and populate the table
    for (var i = 0; i < orders.length; i++) {
        var order = orders[i];
        var row = "<tr>" +
                "<td>" + order.orderId + "</td>" +
                "<td>" + order.userid + "</td>" +
                "<td>" + order.productid + "</td>" +
                "<td>" + order.quantity + "</td>" +
                "<td>" + order.orderDate + "</td>" +
                "<td>" +
                "<button type='button' class='btn btn-primary' onclick='generateInvoice(" +
                "'" + order.orderId + "', '" + order.userid + "', '" + order.productid + "', '" +
                order.quantity + "', '" + order.orderDate + "')'>Generate Invoice</button>" +
                "</td>" +
                "</tr>";
        ordersTableBody.innerHTML += row;
    }
}
