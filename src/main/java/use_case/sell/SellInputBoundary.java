package use_case.sell;

/**
 * Input Boundary for actions which are related to selling.
 */
public interface SellInputBoundary {

    /**
     * Executes the Sell use case.
     * @param sellInputData the input data
     */
    void execute(SellInputData sellInputData);
}
