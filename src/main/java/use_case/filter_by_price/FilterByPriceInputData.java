package use_case.filter_by_price;

/**
 * The Input Data for the Filter By Price Use Case.
 */
public class FilterByPriceInputData {
    private final int price;

    public FilterByPriceInputData(int price) {
        this.price = price;
    }

    int getPrice() {
        return price;
    }
}
