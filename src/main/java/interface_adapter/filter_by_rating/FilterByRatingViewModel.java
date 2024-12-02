package interface_adapter.filter_by_rating;

import interface_adapter.ViewModel;

/**
 * The View Model for the Filter By Rating View.
 */
public class FilterByRatingViewModel extends ViewModel<FilterByRatingState> {

    public FilterByRatingViewModel() {
        super("filter by rating");
        setState(new FilterByRatingState());
    }
}
