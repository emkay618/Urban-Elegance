<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Currency Converter</title>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet"/>
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="network-icon" onmouseover="showForm()" onmouseout="hideForm()">
                <i class="fas fa-globe"></i>
                <div class="tooltip">Currency & Language</div>
            </div>
            <div class="wrapper" id="form-container" style="display: none;">
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
        <!-- Script With Array Of Supported Country Codes -->
        <script src="currency-codes.js"></script>
        <!-- Script with API Key -->
        <script src="api-key.js"></script>
        <!-- Script -->
        <script src="script.js"></script>
        <script>
                // Initialize a variable to track whether the form is open
                let isFormOpen = false;

                function showForm() {
                    // Only show the form if it's not already open
                    if (!isFormOpen) {
                        document.getElementById("form-container").style.display = "block";
                        isFormOpen = true;
                    }
                }

                function hideForm() {
                    // Check if the mouse is still over the icon or the form
                    const icon = document.querySelector(".network-icon");
                    const form = document.getElementById("form-container");
                    const rectIcon = icon.getBoundingClientRect();
                    const rectForm = form.getBoundingClientRect();
                    if (
                            event.clientY < rectIcon.top || event.clientY > rectForm.bottom ||
                            event.clientX < rectIcon.left || event.clientX > rectForm.right
                            ) {
                        // If the mouse is not over the icon or the form, hide the form
                        document.getElementById("form-container").style.display = "none";
                        isFormOpen = false;
                    }
                }
        </script>
    </body>
</html>
