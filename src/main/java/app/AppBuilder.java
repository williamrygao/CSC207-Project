package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.google.cloud.firestore.Firestore;
import data_access.FirebaseInitializer;

import data_access.FirebaseListingDataAccessObject;
import data_access.FirebaseUserDataAccessObject;
import entity.BookFactory;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.remove_from_wishlist.RemoveFromWishlistPresenter;
import interface_adapter.remove_from_wishlist.WishlistViewModel;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellPresenter;
import interface_adapter.sell.SellViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.to_sell_view.ToSellController;
import interface_adapter.to_sell_view.ToSellPresenter;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.back_to_home.BackToHomePresenter;
import interface_adapter.view_wishlist.ViewWishlistController;
import interface_adapter.view_wishlist.ViewWishlistPresenter;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.remove_from_wishlist.RemoveFromWishlistInputBoundary;
import use_case.remove_from_wishlist.RemoveFromWishlistInteractor;
import use_case.remove_from_wishlist.RemoveFromWishlistOutputBoundary;
import use_case.sell.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.to_sell_view.ToSellInputBoundary;
import use_case.to_sell_view.ToSellInteractor;
import use_case.to_sell_view.ToSellOutputBoundary;
import use_case.back_to_home.BackToHomeInputBoundary;
import use_case.back_to_home.BackToHomeInteractor;
import use_case.back_to_home.BackToHomeOutputBoundary;
import use_case.view_wishlist.ViewWishlistInputBoundary;
import use_case.view_wishlist.ViewWishlistInteractor;
import use_case.view_wishlist.ViewWishlistOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    /**
     * New JPanel.
     */
    private final JPanel cardPanel = new JPanel();
    /**
     * New CardLayout.
     */
    private final CardLayout cardLayout = new CardLayout();
    /**
     * New CommonUserFactory.
     */
    private final UserFactory userFactory = new CommonUserFactory();
    private final BookFactory bookFactory = new BookFactory();

    /**
     * New ViewManagerModel.
     */
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    /**
     * New ViewManager.
     */
    private final ViewManager viewManager = new ViewManager(cardPanel,
            cardLayout, viewManagerModel);

    private final Firestore firestore = FirebaseInitializer.getFirestore();
    private final String firebaseBaseURL = "https://csc207project-ed2f9-default-rtdb.firebaseio.com/";

    private final FirebaseUserDataAccessObject userDataAccessObject = new
            FirebaseUserDataAccessObject(userFactory, firebaseBaseURL);

    private final FirebaseListingDataAccessObject listingDataAccessObject = new
            FirebaseListingDataAccessObject(bookFactory, firebaseBaseURL);
    /**
     * SignupView.
     */
    private SignupView signupView;
    /**
     * SignupViewModel.
     */
    private SignupViewModel signupViewModel;
    /**
     * LoginViewModel.
     */
    private LoginViewModel loginViewModel;
    /**
     * HomeViewModel.
     */
    private HomeViewModel homeViewModel;
    /**
     * HomeView.
     */
    private HomeView homeView;
    /**
     * LoginView.
     */
    private LoginView loginView;

    private SellViewModel sellViewModel;
    private SellView sellView;

    private WishlistViewModel wishlistViewModel;
    private WishlistView wishlistView;

    private final SellBookDataFetcher sellBookDataFetcher = new SellBookDataFetcher();

    /**
     * AppBuilder method.
     */
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        homeViewModel = new HomeViewModel();
        homeView = new HomeView(homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    /**
     * Adds the Sell View to the application.
     * @return this builder
     */
    public AppBuilder addSellView() {
        sellViewModel = new SellViewModel();
        sellView = new SellView(sellViewModel);
        cardPanel.add(sellView, sellView.getViewName());
        return this;
    }

    public AppBuilder addWishlistView() {
        wishlistViewModel = new WishlistViewModel();
        wishlistView = new WishlistView(wishlistViewModel);
        cardPanel.add(wishlistView, wishlistView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new
                SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(
                userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(
                viewManagerModel, homeViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, listingDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(
                loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(homeViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject,
                        changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        homeView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(
                viewManagerModel, homeViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject,
                        logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(
                logoutInteractor);
        homeView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the To Sell View Use Case to the application.
     * @return this builder
     */
    public AppBuilder addToSellViewUseCase() {
        final ToSellOutputBoundary toSellOutputBoundary = new ToSellPresenter(
                viewManagerModel, homeViewModel, sellViewModel);

        final ToSellInputBoundary toSellInteractor = new ToSellInteractor(toSellOutputBoundary);

        final ToSellController toSellController = new ToSellController(toSellInteractor);
        homeView.setToSellController(toSellController);
        return this;
    }

    /**
     * Adds the Sell Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSellUseCase() {
        final SellOutputBoundary sellOutputBoundary = new SellPresenter(sellViewModel, homeViewModel);

        final SellInputBoundary sellInteractor =
                new SellInteractor(userDataAccessObject, listingDataAccessObject,
                        sellOutputBoundary, sellBookDataFetcher);

        final SellController sellController = new SellController(
                sellInteractor);
        sellView.setSellController(sellController);
        return this;
    }

    /**
     * Adds the Back To Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBackToHomeUseCase() {
        final BackToHomeOutputBoundary backToHomebackToHomePresenter = new BackToHomePresenter(viewManagerModel,
                homeViewModel);

        final BackToHomeInputBoundary backToHomeInteractor = new BackToHomeInteractor(backToHomebackToHomePresenter);

        final BackToHomeController backToHomeController = new BackToHomeController(backToHomeInteractor);
        sellView.setBackToHomeController(backToHomeController);
        wishlistView.setBackToHomeController(backToHomeController);
        return this;
    }

    /**
     * Adds the View Wishlist Use Case to the application.
     * @return this build
     */
    public AppBuilder addViewWishlistUseCase() {
        final ViewWishlistOutputBoundary viewWishlistPresenter = new ViewWishlistPresenter(viewManagerModel, homeViewModel, wishlistViewModel);

        final ViewWishlistInputBoundary viewWishlistInteractor = new ViewWishlistInteractor(viewWishlistPresenter);

        final ViewWishlistController viewWishlistController = new ViewWishlistController(viewWishlistInteractor);
        homeView.setViewWishlistController(viewWishlistController);
        return this;
    }

    /**
     * Adds the Remove From Wishlist Use Case to the application.
     * @return this build
     */
    public AppBuilder addRemoveFromWishlistUseCase() {
        final RemoveFromWishlistOutputBoundary removeFromWishlistOutputBoundary = new RemoveFromWishlistPresenter(wishlistViewModel);
        final RemoveFromWishlistInputBoundary removeFromWishlistInteractor = new RemoveFromWishlistInteractor(userDataAccessObject, removeFromWishlistOutputBoundary, userFactory);
        final RemoveFromWishlistController removeFromWishlistController = new RemoveFromWishlistController(removeFromWishlistInteractor, wishlistView);
        wishlistView.setRemoveFromWishlistController(removeFromWishlistController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView
     * to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Joe Repka Bookstore");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
