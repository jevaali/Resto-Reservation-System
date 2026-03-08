export async function getRestaurants() {
    const response = await fetch("/api/restaurants");
    return await response.json();
}

export async function createRestaurant(restaurantData) {
    console.log('DEBUG: createRestaurant API called with data:', restaurantData);
    
    const response = await fetch("/api/restaurants", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(restaurantData)
    });
    
    console.log('DEBUG: API response status:', response.status);
    console.log('DEBUG: API response status text:', response.statusText);
    
    if (!response.ok) {
        let errorDetail = '';
        try {
            const errorData = await response.json();
            console.log('DEBUG: API error response:', errorData);
            errorDetail = errorData.message || errorData.error || '';
        } catch (parseError) {
            console.log('DEBUG: Failed to parse error response:', parseError);
            errorDetail = await response.text();
        }
        
        throw new Error(`Failed to create restaurant: ${response.status} - ${errorDetail}`);
    }
    
    const data = await response.json();
    console.log('DEBUG: API success response:', data);
    return data;
}