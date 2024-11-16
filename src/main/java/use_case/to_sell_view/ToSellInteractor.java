package use_case.to_sell_view;

/**
 * The To Sell Interactor.
 */
public class ToSellInteractor implements ToSellInputBoundary {
    private ToSellOutputBoundary toSellPresenter;

    public ToSellInteractor(final ToSellOutputBoundary toSellOutputBoundary) {
        this.toSellPresenter = toSellOutputBoundary;
    }

    /**
     * Override execute method.
     * @param toSellInputData the input data
     */
    @Override
    public void execute(final ToSellInputData toSellInputData) {
        final String username = toSellInputData.getUsername();

        if (username == null) {
            toSellPresenter.prepareFailView("Username not found.");
        }
        else {
            final ToSellOutputData toSellOutputData = new ToSellOutputData(
                    username, false);
            toSellPresenter.prepareSuccessView(toSellOutputData);
        }
    }
}
