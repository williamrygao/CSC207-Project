package use_case.back_to_home;

public interface BackToHomeOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
