## 1. Universal Principles of Design
### 1.1 Equitable Use
* All features are identically available to all users; in fact, users are indistinguishable aside from their self-determined username.
* The only barrier to the design are the sign up and login steps, which are familiar to most software users.

### 1.2 Flexibility in Use
* Sorting and filtering provide two ways to accomplish similar tasks. Users can organize the listings on Home View
according to title, author, price, rating, and wishlist status. Alternatively, users may use the "filter by-" use cases
or the view wishlist use case to isolate the listings with the desired property. This provides flexibility in users's 
preferred method of refining their search.
* The wishlist may be updated from the Home View or from the specific Wishlist View.

### 1.3 Simple and Intuitive Use
* Task completion feedback for adding and removing listings from wishlist, as well as listing books for sale.
* The sell use case could be improved in this aspect, because it currently requires the seller to retrieve and input the
Google Books ID associated with their book.

### 1.4 Perceptible Information
* Each listing is concisely displayed by its title, author, price, rating, and wishlist status.
* Wishlist status shown by a simple checkbox.
* Buttons are organized into two groups: the Sell A Book and Wishlist buttons above the table of listings are related to
the bookstore features, while the Log Out and Change Password buttons below are more administrative in nature.

### 1.5 Tolerance for Error
* For use cases in which failure is possible, a prepareFailView() method has been implemented to address any unintended actions.
* The sell use case, one of the more hazardous of this program, is isolated in a separate view.
* When a user removes a listing from their wishlist in the Wishlist View, the listing is not removed from the view until the next time the user opens the Wishlist View.
This allows users to re-add listings to their wishlist while staying in the view in case they accidentally removed a listing
or change their mind.


### 1.6 Low Physical Effort
* User interface balances buttons, text inputs, and checkboxes to avoid repetitiveness while minimizing physical demands.
* The Home View displays listings in an organized table that permits intuitive navigation functions such as scrolling and sorting by clicking the column headers. 

### 1.7 Size and Space for Approach and Use
* Our program only relies on typing and clicking / dragging of the cursor.
* To accommodate users who have difficulty typing, a speech-to-text feature would be helpful. 

## 2. Target Audience
The main features of Joe's Online Bookstore are discovering, purchasing, or selling books. Hence this software would be
primarily marketed to avid readers, book collectors, or sellers. The discovery and purchasing features would be helpful
to readers and collectors who wish to find specific books or types of books, because the online format of the bookstore
transgresses several barriers such as lack of selection in physical bookstores. People who wish to sell their books would
certainly appreciate the convenience and wide reach of an online bookstore, because they may attract and quickly correspond with
customers from any location. Moreover, the bookstore's flexibility of use may also attract more casual users such as students
looking to buy textbooks or gift-buyers. The search feature would be particularly useful for these two types of users,
who would likely have an exact title they intend to purchase. The bookstore would provide a strong selection of books and
a variety of sellers and prices to choose from.

## 3. Demographic Limitations
The main demographic concern with Joe's Online Bookstore is age, because elderly users may be less likely to use online
bookstores in general. For example, they may be accustomed to visiting physical bookstores so that they may physically view and
interact with the book before deciding to purchase.

While economic class may affect the software's availability to users who do not have reliable access to computers or the Internet,
the wishlist feature minimizes relational harm in this aspect by allowing users to browse and interact with listings 
despite not immediately having the funds to purchase.
