package interface_adapter.to_search_view;

import use_case.to_search_view.ToSearchInputBoundary;

/**
 * The controller for the To Search Case.
 */
public class ToSearchController {

    private ToSearchInputBoundary toSearchUseCaseInteractor;

    /**
     * ToSearchController method.
     * @param toSearchUseCaseInteractor the use case interactor
     */
    public ToSearchController(final ToSearchInputBoundary toSearchUseCaseInteractor) {
        this.toSearchUseCaseInteractor = toSearchUseCaseInteractor;
    }

    /**
     * Executes the To Search Use Case.
     */
    public void execute() {
        toSearchUseCaseInteractor.execute();
    }
}
