package use_case.to_filter_by_price;

/**
 * The To Filter By Price Interactor.
 */
public class ToFilterByPriceInteractor implements ToFilterByPriceInputBoundary {
    private final ToFilterByPriceOutputBoundary toFilterByPricePresenter;

    public ToFilterByPriceInteractor(final ToFilterByPriceOutputBoundary toFilterByPriceOutputBoundary) {
        this.toFilterByPricePresenter = toFilterByPriceOutputBoundary;
    }

    /**
     * Override execute method.
     */
    @Override
    public void execute() {
        toFilterByPricePresenter.prepareSuccessView();
    }
}
