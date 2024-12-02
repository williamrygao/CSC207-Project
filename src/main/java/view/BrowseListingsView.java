package view;

import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.filter_by_rating.BrowseListingsViewModel;
import interface_adapter.to_filter_by_rating_view.ToFilterByRatingController;
import interface_adapter.to_filter_by_rating_view.ToFilterByRatingPresenter;
import interface_adapter.to_search_view.ToSearchController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;

/**
 * The View for when the user wants to browse books by name, genre, or rating.
 */
public class BrowseListingsView extends JPanel implements PropertyChangeListener {

    // view name and view model
    private final String viewName = "browsing";
    private final BrowseListingsViewModel browseListingsViewModel;

    // controllers for the buttons
    private ToSearchController toSearchController;
    private ToFilterByRatingController toFilterByRatingController;
    private BackToHomeController backToHomeController;

    // buttons
    private final JButton browseByName;
    private final JButton browseByGenre;
    private final JButton browseByRating;
    private final JButton back;

    public BrowseListingsView(BrowseListingsViewModel browseListingsViewModel) {
        this.browseListingsViewModel = browseListingsViewModel;
        this.browseListingsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Browse Listings");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        browseByName = new JButton("Browse by Name");
        browseByGenre = new JButton("Browse by Genre");
        browseByRating = new JButton("Browse by Rating");
        back = new JButton("Back");

        final JPanel buttons = new JPanel();
        buttons.add(browseByName);
        buttons.add(browseByGenre);
        buttons.add(browseByRating);
        buttons.add(back);

        // actions for when the buttons are pressed
        browseByName.addActionListener(
                evt -> {
                    if (evt.getSource().equals(browseByName)) {
                        toSearchController.execute();
                    }
                }
        );
        browseByGenre.addActionListener(
                evt -> {
                    if (evt.getSource().equals(browseByGenre)) {
                        // wait for the browse by genre use case to be finished
                    }
                }
        );
        browseByRating.addActionListener(
                evt -> {
                    if (evt.getSource().equals(browseByRating)) {
                        toFilterByRatingController.execute();
                    }
                }
        );
        back.addActionListener(
                evt -> {
                    if (evt.getSource().equals(back)) {
                        backToHomeController.execute();
                    }
                }
        );
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    // methods to set the controllers for the buttons
    public void setToSearchController(ToSearchController toSearchController) {
        this.toSearchController = toSearchController;
    }

    public void setToFilterByRatingController(ToFilterByRatingController toFilterByRatingController) {
        this.toFilterByRatingController = toFilterByRatingController;
    }

    public void setBackToHomeController(BackToHomeController backToHomeController) {
        this.backToHomeController = backToHomeController;
    }
}
