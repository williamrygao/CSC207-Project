package interface_adapter.filter_by_rating;

import use_case.filter_by_rating.FilterByRatingOutputBoundary;
import use_case.filter_by_rating.FilterByRatingOutputData;

// add that it implements the filter by rating output boundary
public class FilterByRatingPresenter {

    // All the relevant view models as attributes

    // Initialize the class with the view models
    public FilterByRatingPresenter() {

    }

    public void prepareSuccessView(FilterByRatingOutputData filterByRatingOutputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
