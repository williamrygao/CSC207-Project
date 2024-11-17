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
     */
    @Override
    public void execute() {
        toSellPresenter.prepareSuccessView();
    }
}
