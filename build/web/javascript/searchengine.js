// Function to fetch product data from the server
const fetchProductData = async () => {
    try {
        const response = await fetch('productData'); // Fetch from the correct servlet URL
        if (!response.ok) {
            throw new Error('Failed to fetch product data');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching product data:', error.message);
        return [];
    }
};

// Function to display items
const displayItems = (items) => {
    const rootElement = document.getElementById('root');
    rootElement.innerHTML = items.map(item => {
        const { productId, imageUrl, name, price } = item;
        return `
            <div class='box'>
                <div class='img-box'>
                    <img class='images' src='${imageUrl}' alt='${name}'></img>
                </div> 
                <div class='bottom'>
                    <p>${name}</p>
                    <h2>$ ${price}.00</h2>
                    <button>Add to cart</button>
                </div>
            </div>`;
    }).join('');
};

// Event listener for search input
document.getElementById('searchBar').addEventListener('keyup', async (e) => {
    const searchData = e.target.value.toLowerCase();
    const products = await fetchProductData();
    const filteredData = products.filter(item => item.name.toLowerCase().includes(searchData));
    displayItems(filteredData);
});

// Initial display of items
fetchProductData().then(displayItems);
