package view;

import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.remove_from_wishlist.WishlistState;
import interface_adapter.sell.SellState;
import interface_adapter.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.remove_from_wishlist.WishlistViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WishlistView extends JPanel implements PropertyChangeListener {

    private final String viewName = "wishlist";
    private final WishlistViewModel wishlistViewModel;
    private BackToHomeController backToHomeController;
    private RemoveFromWishlistController removeFromWishlistController;

    private final JLabel username;

    private final JButton back;

    public WishlistView(WishlistViewModel wishlistViewModel) {
        this.wishlistViewModel = wishlistViewModel;
        this.wishlistViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Wishlist Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(back)) {
                        backToHomeController.execute();
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("wishlist")) {
            final WishlistState state = (WishlistState) evt.getNewValue();
        }
    }

    public void setRemoveFromWishlistController(RemoveFromWishlistController removeFromWishlistController) {
        this.removeFromWishlistController = removeFromWishlistController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }
}
