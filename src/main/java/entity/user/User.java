package entity.user;

import java.util.List;

import entity.listing.Listing;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the wishlist of the user.
     * @return the wishlist of the user.
     */
    List<Listing> getWishlist();
}
