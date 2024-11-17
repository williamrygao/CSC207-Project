package use_case.back_to_home;

public interface BackToHomeOutputBoundary {
    public abstract void prepareSuccessView();

    void prepareFailView(String error);
}
