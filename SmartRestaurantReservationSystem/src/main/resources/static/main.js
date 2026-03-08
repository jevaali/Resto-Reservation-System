import {getRestaurants} from "./js/api/restaurantApi.js";
import {renderRestaurants} from "./ui/restaurantRenderer.js";

async function init(){

    const restaurants = await getRestaurants();

    renderRestaurants(restaurants);
}

init();