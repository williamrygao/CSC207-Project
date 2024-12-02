package interface_adapter.to_filter_by_price;

import use_case.to_filter_by_price.ToFilterByPriceInputBoundary;

/**
 * The controller for the To Filter By Price Use Case.
 */
public class ToFilterByPriceController {

    private final ToFilterByPriceInputBoundary toFilterByPriceInteractor;

    /**
     * ToFilterByPriceController constructor method.
     * @param toFilterByPriceInteractor the use case interactor
     */
    public ToFilterByPriceController(final ToFilterByPriceInputBoundary toFilterByPriceInteractor) {
        this.toFilterByPriceInteractor = toFilterByPriceInteractor;
    }

    /**
     * Executes the To Filter By Price Use Case.
     */
    public void execute() {
        toFilterByPriceInteractor.execute();
    }
}
