package use_case.back_to_signup;

public interface BackToSignupOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
