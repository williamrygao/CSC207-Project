package app;

import data_access.FirebaseInitializer;

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
        FirebaseInitializer.initializeFirebase();
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder.addLoginView().addSignupView()
                                            .addHomeView()
                                            .addSellView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addToSellViewUseCase()
                                            .addSellUseCase()
                                            .addBackToHomeUseCase()
                                            .addChangePasswordUseCase().build();

        application.pack();
        application.setVisible(true);
    }
}
