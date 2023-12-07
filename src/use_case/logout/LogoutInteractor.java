package use_case.logout;

public class LogoutInteractor implements LogoutInputBoundary{
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutPresenter){
        this.logoutPresenter = logoutPresenter;
    }

    public void execute(LogoutInputData inputData){
        LogoutOutputData logoutOutputData = new LogoutOutputData(inputData.getUsername());
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
