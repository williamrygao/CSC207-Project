package interface_adapter.filter_by_price;

import interface_adapter.ViewModel;

/**
 * The View Model for the Filter By Price View.
 */
public class FilterByPriceViewModel extends ViewModel<FilterByPriceState> {

    public FilterByPriceViewModel() {
        super("filter by price");
        setState(new FilterByPriceState());
    }
}
