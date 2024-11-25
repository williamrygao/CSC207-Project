package use_case.filter_by_rating;

public interface FilterByRatingOutputBoundary {
    void prepareSuccessView(FilterByRatingOutputData filterByRatingOutputData);

    void prepareFailView(String errorMessage);
}
