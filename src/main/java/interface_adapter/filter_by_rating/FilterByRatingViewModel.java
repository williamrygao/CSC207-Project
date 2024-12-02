package interface_adapter.filter_by_rating;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

public class FilterByRatingViewModel extends ViewModel<FilterByRatingState> {

    public FilterByRatingViewModel() {
        super("search");
        setState(new SearchState());
    }
}
