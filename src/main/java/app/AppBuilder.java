package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.google.cloud.firestore.Firestore;
import data_access.FirebaseInitializer;
import data_access.FirebaseListingDataAccessObject;
import data_access.FirebaseRatingDataAccessObject;
import data_access.FirebaseUserDataAccessObject;
import entity.book.BookFactory;
import entity.user.CommonUserFactory;
import entity.user.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.filter_by_price.FilterByPriceController;
import interface_adapter.filter_by_price.FilterByPricePresenter;
import interface_adapter.filter_by_price.FilterByPriceViewModel;
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingPresenter;
import interface_adapter.to_filter_by_price.ToFilterByPriceController;
import interface_adapter.to_filter_by_price.ToFilterByPricePresenter;
import interface_adapter.wishlist.add_to_wishlist.AddToWishlistController;
import interface_adapter.wishlist.add_to_wishlist.AddToWishlistPresenter;
import interface_adapter.back_to_home.BackToHomeController;
import interface_adapter.back_to_home.BackToHomePresenter;
import interface_adapter.back_to_signup.BackToSignupController;
import interface_adapter.back_to_signup.BackToSignupPresenter;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.HomeViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistController;
import interface_adapter.wishlist.remove_from_wishlist.RemoveFromWishlistPresenter;
import interface_adapter.wishlist.WishlistViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sell.SellController;
import interface_adapter.sell.SellPresenter;
import interface_adapter.sell.SellViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.to_search_view.ToSearchController;
import interface_adapter.to_search_view.ToSearchPresenter;
import interface_adapter.to_sell.ToSellController;
import interface_adapter.to_sell.ToSellPresenter;
import interface_adapter.update_listings.UpdateListingsController;
import interface_adapter.update_listings.UpdateListingsPresenter;
import interface_adapter.wishlist.view_wishlist.ViewWishlistController;
import interface_adapter.wishlist.view_wishlist.ViewWishlistPresenter;
import use_case.filter_by_price.FilterByPriceInputBoundary;
import use_case.filter_by_price.FilterByPriceInteractor;
import use_case.filter_by_price.FilterByPriceOutputBoundary;
import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInteractor;
import use_case.leave_rating.LeaveRatingOutputBoundary;
import use_case.to_filter_by_price.ToFilterByPriceInputBoundary;
import use_case.to_filter_by_price.ToFilterByPriceInteractor;
import use_case.to_filter_by_price.ToFilterByPriceOutputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistInputBoundary;
import use_case.wishlist.add_to_wishlist.AddToWishlistInteractor;
import use_case.wishlist.add_to_wishlist.AddToWishlistOutputBoundary;
import use_case.back_to_home.BackToHomeInputBoundary;
import use_case.back_to_home.BackToHomeInteractor;
import use_case.back_to_home.BackToHomeOutputBoundary;
import use_case.back_to_signup.BackToSignupInputBoundary;
import use_case.back_to_signup.BackToSignupInteractor;
import use_case.back_to_signup.BackToSignupOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistInputBoundary;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistInteractor;
import use_case.wishlist.remove_from_wishlist.RemoveFromWishlistOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.sell.SellInputBoundary;
import use_case.sell.SellInteractor;
import use_case.sell.SellOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.to_search_view.ToSearchInputBoundary;
import use_case.to_search_view.ToSearchInteractor;
import use_case.to_search_view.ToSearchOutputBoundary;
import use_case.to_sell_view.ToSellInputBoundary;
import use_case.to_sell_view.ToSellInteractor;
import use_case.to_sell_view.ToSellOutputBoundary;
import use_case.update_listings.UpdateListingsInputBoundary;
import use_case.update_listings.UpdateListingsInteractor;
import use_case.update_listings.UpdateListingsOutputBoundary;
import use_case.wishlist.view_wishlist.ViewWishlistInputBoundary;
import use_case.wishlist.view_wishlist.ViewWishlistInteractor;
import use_case.wishlist.view_wishlist.ViewWishlistOutputBoundary;
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

    private final Firestore firestore = FirebaseInitializer.initializeFirebase();
    private final String firebaseBaseURL = "https://csc207project-ed2f9-default-rtdb.firebaseio.com/";

    private final FirebaseUserDataAccessObject userDataAccessObject = new
            FirebaseUserDataAccessObject(userFactory, firebaseBaseURL);

    private final FirebaseListingDataAccessObject listingDataAccessObject = new
            FirebaseListingDataAccessObject(firebaseBaseURL);

    private final FirebaseRatingDataAccessObject ratingDataAccessObject = new
            FirebaseRatingDataAccessObject(firebaseBaseURL);
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
    private SearchViewModel searchViewModel;
    private FilterByPriceViewModel filterByPriceViewModel;
    private SellView sellView;
    private SearchView searchView;
    private FilterByPriceView filterByPriceView;

    private WishlistViewModel wishlistViewModel;
    private WishlistView wishlistView;

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

    /**
     * Adds the Search View to the application.
     * @return this builder
     */
    public AppBuilder addSearchView() {
        searchViewModel = new SearchViewModel();
        searchView = new SearchView(searchViewModel);
        cardPanel.add(searchView, searchView.getViewName());
        return this;
    }

    /**
     * Adds the Filter By Price View to the application.
     * @return this builder
     */
    public AppBuilder addFilterByPriceView() {
        filterByPriceViewModel = new FilterByPriceViewModel();
        filterByPriceView = new FilterByPriceView(filterByPriceViewModel);
        cardPanel.add(filterByPriceView, filterByPriceView.getViewName());
        return this;
    }

    /**
     * Adds the Wishlist View to the application.
     * @return this builder
     */
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
                userDataAccessObject, loginOutputBoundary);

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
     *
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
                new SellInteractor(listingDataAccessObject, sellOutputBoundary, bookFactory);

        final SellController sellController = new SellController(
                sellInteractor);
        sellView.setSellController(sellController);
        return this;
    }

    /**
     * Adds the To Search View Use Case to the application.
     * @return this builder
     */
    public AppBuilder addToSearchViewUseCase() {
        final ToSearchOutputBoundary toSearchOutputBoundary = new ToSearchPresenter(
                viewManagerModel, homeViewModel, searchViewModel);

        final ToSearchInputBoundary toSearchInteractor = new ToSearchInteractor(toSearchOutputBoundary);

        final ToSearchController toSearchController = new ToSearchController(toSearchInteractor);
        homeView.setToSearchController(toSearchController);
        return this;
    }

    /**
     * Adds the To Filter By Price Use Case to the application.
     * @return this builder
     */
    public AppBuilder addToFilterByPriceViewUseCase() {
        final ToFilterByPriceOutputBoundary toFilterByPriceOutputBoundary =
                new ToFilterByPricePresenter(viewManagerModel, homeViewModel, filterByPriceViewModel);

        final ToFilterByPriceInputBoundary toFilterByRatingInteractor =
                new ToFilterByPriceInteractor(toFilterByPriceOutputBoundary);

        final ToFilterByPriceController toFilterByPriceController =
                new ToFilterByPriceController(toFilterByRatingInteractor);

        homeView.setToFilterByRatingController(toFilterByPriceController);
        return this;
    }

    /**
     * Adds the Filter By Rating Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFilterByPriceUseCase() {
        final FilterByPriceOutputBoundary filterByPricePresenter =
                new FilterByPricePresenter(filterByPriceViewModel, homeViewModel);

        final FilterByPriceInputBoundary filterByPriceInteractor =
                new FilterByPriceInteractor(listingDataAccessObject, filterByPricePresenter);

        final FilterByPriceController filterByPriceController =
                new FilterByPriceController(filterByPriceInteractor);

        filterByPriceView.setFilterByPriceController(filterByPriceController);
        return this;
    }

    /**
     * Adds the Search Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel);

        final SearchInputBoundary searchInteractor =
                new SearchInteractor(listingDataAccessObject, searchOutputBoundary);

        final SearchController searchController = new SearchController(
                searchInteractor);
        searchView.setSearchController(searchController);
        return this;
    }

    /**
     * Adds the Back To Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBackToSignupUseCase() {
        final BackToSignupOutputBoundary backToSignupPresenter =
                new BackToSignupPresenter(viewManagerModel, signupViewModel);

        final BackToSignupInputBoundary backToSignupInteractor = new BackToSignupInteractor(backToSignupPresenter);

        final BackToSignupController backToSignupController = new BackToSignupController(backToSignupInteractor);
        loginView.setBackToSignupController(backToSignupController);
        return this;
    }

    /**
     * Adds the Back To Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBackToHomeUseCase() {
        final BackToHomeOutputBoundary backToHomePresenter =
                new BackToHomePresenter(viewManagerModel, homeViewModel);

        final BackToHomeInputBoundary backToHomeInteractor = new BackToHomeInteractor(backToHomePresenter);

        final BackToHomeController backToHomeController = new BackToHomeController(backToHomeInteractor);
        sellView.setBackToHomeController(backToHomeController);
        searchView.setBackToHomeController(backToHomeController);
        filterByPriceView.setBackToHomeController(backToHomeController);
        wishlistView.setBackToHomeController(backToHomeController);
        return this;
    }

    /**
     * Adds the View Wishlist Use Case to the application.
     * @return this build
     */
    public AppBuilder addViewWishlistUseCase() {
        final ViewWishlistOutputBoundary viewWishlistPresenter =
                new ViewWishlistPresenter(viewManagerModel, homeViewModel, wishlistViewModel);

        final ViewWishlistInputBoundary viewWishlistInteractor =
                new ViewWishlistInteractor(userDataAccessObject, viewWishlistPresenter);

        final ViewWishlistController viewWishlistController = new ViewWishlistController(viewWishlistInteractor);
        homeView.setViewWishlistController(viewWishlistController);
        return this;
    }

    /**
     * Adds the Remove From Wishlist Use Case to the application.
     * @return this build
     */
    public AppBuilder addRemoveFromWishlistUseCase() {
        final RemoveFromWishlistOutputBoundary removeFromWishlistOutputBoundary =
                new RemoveFromWishlistPresenter(wishlistViewModel, homeViewModel, viewManagerModel);
        final RemoveFromWishlistInputBoundary removeFromWishlistInteractor =
                new RemoveFromWishlistInteractor(userDataAccessObject, removeFromWishlistOutputBoundary);
        final RemoveFromWishlistController removeFromWishlistController =
                new RemoveFromWishlistController(removeFromWishlistInteractor);
        homeView.setRemoveFromWishlistController(removeFromWishlistController);
        wishlistView.setRemoveFromWishlistController(removeFromWishlistController);
        return this;
    }

    /**
     * Adds the Update Listings Use Case to the application.
     * @return this build
     */
    public AppBuilder addUpdateListingsUseCase() {
        final UpdateListingsOutputBoundary updateListingsOutputBoundary = new UpdateListingsPresenter(homeViewModel);
        final UpdateListingsInputBoundary updateListingsInteractor = new UpdateListingsInteractor(userDataAccessObject, listingDataAccessObject, updateListingsOutputBoundary);
        final UpdateListingsController updateListingsController = new UpdateListingsController(updateListingsInteractor);
        homeView.setUpdateListingsController(updateListingsController);
        return this;
    }

    /**
     * Adds the Add To Wishlist Use Case to the application.
     * @return this build
     */
    public AppBuilder addAddToWishlistUseCase() {
        final AddToWishlistOutputBoundary addToWishlistOutputBoundary =
                new AddToWishlistPresenter(wishlistViewModel, homeViewModel, viewManagerModel);
        final AddToWishlistInputBoundary addToWishlistInteractor =
                new AddToWishlistInteractor(userDataAccessObject, addToWishlistOutputBoundary);
        final AddToWishlistController addToWishlistController = new AddToWishlistController(addToWishlistInteractor);
        homeView.setAddToWishlistController(addToWishlistController);
        wishlistView.setAddToWishlistController(addToWishlistController);
        return this;
    }
    /**
     * Adds the Leave Rating Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLeaveRatingUseCase() {
        // Create the Output Boundary (Presenter)
        final LeaveRatingOutputBoundary leaveRatingOutputBoundary = new LeaveRatingPresenter(viewManagerModel,homeViewModel);

        final LeaveRatingInputBoundary leaveRatingInteractor = new LeaveRatingInteractor(ratingDataAccessObject, leaveRatingOutputBoundary);

        // Create the Controller
        final LeaveRatingController leaveRatingController = new LeaveRatingController(leaveRatingInteractor);

        // Set the Controller in the Home View
        homeView.setToLeaveRatingController(leaveRatingController);

        return this;
    }


    /**
     * Creates the JFrame for the application and initially sets the SignupView
     * to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Joe Repka's Bookstore");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
