package interface_adapter.to_sell;

import use_case.to_sell_view.ToSellInputBoundary;

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
     */
    public void execute() {
        toSellUseCaseInteractor.execute();
    }
}
