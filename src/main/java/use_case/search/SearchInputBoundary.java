package use_case.search;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface SearchInputBoundary {

    /**
     * Executes the Search use case.
     * @param searchInputData the input data
     * @return books that match the search query
     */
    void execute(SearchInputData searchInputData);
}
