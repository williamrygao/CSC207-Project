package interface_adapter.filter_by_genre;

import interface_adapter.ViewModel;

/**
 * ViewModel for the "Filter Books by Genre" use case.
 */
public class FilterByGenreViewModel extends ViewModel<FilterByGenreState> {
    public FilterByGenreViewModel() {
        super("filter by genre");
        setState(new FilterByGenreState());
    }
}
