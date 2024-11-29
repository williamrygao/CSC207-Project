package use_case.to_search_view;

/**
 * The output boundary for the To Search View Use Case.
 */
public interface ToSearchOutputBoundary {
    /**
     * Prepares the success view for the To Search Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
