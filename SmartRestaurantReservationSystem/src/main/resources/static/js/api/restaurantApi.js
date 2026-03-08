
export async function getRestaurants(){

    const response = await fetch("/api/restaurants");

    return await response.json();
}