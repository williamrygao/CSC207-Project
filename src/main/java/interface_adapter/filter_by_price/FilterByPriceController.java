package interface_adapter.filter_by_price;

import use_case.filter_by_price.FilterByPriceInputBoundary;
import use_case.filter_by_price.FilterByPriceInputData;

import javax.swing.*;
import java.awt.*;

/**
 * The controller for the Filter By Price Use Case.
 */
public class FilterByPriceController {

    private final FilterByPriceInputBoundary filterByPriceInteractor;

    public FilterByPriceController(FilterByPriceInputBoundary filterByPriceInteractor) {
        this.filterByPriceInteractor = filterByPriceInteractor;
    }

    /**
     * Executes the Filter By Price Use Case.
     * @param maxPrice the max price of books that the user wants to filter by
     */
    public void execute(int maxPrice) {
        final FilterByPriceInputData filterByPriceInputData = new FilterByPriceInputData(maxPrice);
        filterByPriceInteractor.execute(filterByPriceInputData);
    }

    /**
     * Executes error messages for the filter by price use case.
     * @param errorMessage the error message to display to user
     * @param center where the panel will be centered
     */
    public void error(Component center, String errorMessage) {
        JOptionPane.showMessageDialog(center, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

