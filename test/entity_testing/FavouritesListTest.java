package entity_testing;

import entity.FavouritesList;
import entity.Restaurant;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class FavouritesListTest {

    @Test
    void testAddToEmptyFavouritesList() {
        FavouritesList favouritesList = new FavouritesList();
        assertTrue(favouritesList.isEmpty());

        Restaurant restaurant = new Restaurant("1", "Restaurant A", "Address A", "1234567890", null, "imageURL");
        favouritesList.add(restaurant);

        assertFalse(favouritesList.isEmpty());
        assertTrue(favouritesList.contains("1"));
    }

    @Test
    void testRemoveFromFavouritesList() {
        FavouritesList favouritesList = new FavouritesList();
        Restaurant restaurant = new Restaurant("1", "Restaurant A", "Address A", "1234567890", null, "imageURL");
        favouritesList.add(restaurant);

        assertTrue(favouritesList.contains("1"));

        favouritesList.remove("1");
        assertFalse(favouritesList.contains("1"));
        assertTrue(favouritesList.isEmpty());
    }

    @Test
    void testFavouritesListIterator() {
        FavouritesList favouritesList = new FavouritesList();
        Restaurant restaurant1 = new Restaurant("1", "Restaurant A", "Address A", "1234567890", null, "imageURL");
        Restaurant restaurant2 = new Restaurant("2", "Restaurant B", "Address B", "0987654321", null, "imageURL");
        favouritesList.add(restaurant1);
        favouritesList.add(restaurant2);

        Iterator<Restaurant> iterator = favouritesList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(restaurant1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(restaurant2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFavouritesListToString() {
        FavouritesList favouritesList = new FavouritesList();
        Restaurant restaurant1 = new Restaurant("1", "Restaurant A", "Address A", "1234567890", null, "imageURL");
        Restaurant restaurant2 = new Restaurant("2", "Restaurant B", "Address B", "0987654321", null, "imageURL");
        favouritesList.add(restaurant1);
        favouritesList.add(restaurant2);

        String expected = "1,2";
        assertEquals(expected, favouritesList.toString());
    }
}
