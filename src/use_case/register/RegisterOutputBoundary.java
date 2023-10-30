package use_case.register;

public interface RegisterOutputBoundary {
    void prepareSuccessView(RegisterOutputData user);

    void prepareFailView(String error);
}