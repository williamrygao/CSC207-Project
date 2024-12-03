package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(final String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder.addLoginView().addSignupView().addHomeView()
                                            .addSellView().addSearchView().addWishlistView().addFilterByPriceView()
                                            .addSignupUseCase().addLoginUseCase().addLogoutUseCase()
                                            .addToSellViewUseCase().addToSearchViewUseCase()
                                            .addToFilterByPriceViewUseCase().addSellUseCase().addSearchUseCase()
                                            .addFilterByPriceUseCase().addBackToHomeUseCase().addViewWishlistUseCase()
                                            .addRemoveFromWishlistUseCase().addAddToWishlistUseCase().addLeaveRatingUseCase()
                                            .addBackToSignupUseCase().addChangePasswordUseCase()
                                            .addUpdateListingsUseCase().build();

        application.pack();
        application.setVisible(true);
    }
}
