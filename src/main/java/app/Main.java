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
        final JFrame application = appBuilder.addLoginView().addSignupView()
                                            .addHomeView()
                                            .addSellView()
                                            .addWishlistView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addToSellViewUseCase()
                                            .addSellUseCase()
                                            .addBackToHomeUseCase().addViewWishlistUseCase()
                                            .addRemoveFromWishlistUseCase()
                                            .addChangePasswordUseCase().build();

        application.pack();
        application.setVisible(true);
    }
}
