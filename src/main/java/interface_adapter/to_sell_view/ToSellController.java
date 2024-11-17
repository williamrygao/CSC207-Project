package interface_adapter.to_sell_view;

import use_case.to_sell_view.ToSellInputBoundary;
import use_case.to_sell_view.ToSellInputData;

/**
 * The controller for the To Sell Case.
 */
public class ToSellController {

    private ToSellInputBoundary toSellUseCaseInteractor;

    /**
     * ToSellController method.
     * @param toSellUseCaseInteractor the use case interactor
     */
    public ToSellController(final ToSellInputBoundary toSellUseCaseInteractor) {
        this.toSellUseCaseInteractor = toSellUseCaseInteractor;
    }

    /**
     * Executes the To Sell Use Case.
     * @param username the username of the user going to sell
     */
    public void execute(final String username) {
        final ToSellInputData toSellInputData = new ToSellInputData(username);
        toSellUseCaseInteractor.execute(toSellInputData);
    }
}
