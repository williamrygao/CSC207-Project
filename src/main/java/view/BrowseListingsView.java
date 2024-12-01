package view;

import interface_adapter.filter_by_rating.BrowseListingsViewModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;

/**
 * The View for when the user wants to browse books by name, genre, or rating.
 */
public class BrowseListingsView extends JPanel implements PropertyChangeListener {

    private final BrowseListingsViewModel browseListingsViewModel;
    private final String viewName = "browsing";

    private final JButton browseByName;
    private final JButton browseByGenre;
    private final JButton browseByRating;
    private final JButton back;

    public BrowseListingsView(BrowseListingsViewModel browseListingsViewModel) {
        this.browseListingsViewModel = browseListingsViewModel;

        final JLabel title = new JLabel("Browse Listings");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        browseByName = new JButton("Browse by name");
        browseByGenre = new JButton("Browse by genre");
        browseByRating = new JButton("Browse by rating");
        back = new JButton("Back");

        final JPanel buttons = new JPanel();
        buttons.add(browseByName);
        buttons.add(browseByGenre);
        buttons.add(browseByRating);
        buttons.add(back);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
