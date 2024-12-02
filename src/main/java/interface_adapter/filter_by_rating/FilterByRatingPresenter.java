package interface_adapter.filter_by_rating;

import use_case.filter_by_rating.FilterByRatingOutputBoundary;
import use_case.filter_by_rating.FilterByRatingOutputData;

public class FilterByRatingPresenter implements FilterByRatingOutputBoundary {

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
