package use_case.register;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class RegisterInteractor implements RegisterInputBoundary {
    final RegisterUserDataAccessInterface userDataAccessObject;
    final RegisterOutputBoundary userPresenter;
    final UserFactory userFactory;

    public RegisterInteractor(RegisterUserDataAccessInterface signupDataAccessInterface,
                              RegisterOutputBoundary registerOutputBoundary,
                              UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = registerOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(RegisterInputData registerInputData) {
        if (userDataAccessObject.existsByName(registerInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists, please use another username.");
        } else if (!registerInputData.getPassword().equals(registerInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(registerInputData.getUsername(), registerInputData.getPassword(), now);
            userDataAccessObject.save(user);

            RegisterOutputData registerOutputData = new RegisterOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(registerOutputData);
        }
    }
}