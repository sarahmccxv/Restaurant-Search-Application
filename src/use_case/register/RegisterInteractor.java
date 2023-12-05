package use_case.register;

import api.yelp.YelpApiServices;
import entity.User;
import entity.UserFactory;
import java.util.Random;

import java.time.LocalDateTime;

public class RegisterInteractor implements RegisterInputBoundary {
    final RegisterUserDataAccessInterface userDataAccessObject;
    final RegisterOutputBoundary userPresenter;
    final UserFactory userFactory;
    final YelpApiServices APIRestaurantDataAccessObject;

    public RegisterInteractor(RegisterUserDataAccessInterface signupDataAccessInterface,
                              YelpApiServices APIRestaurantDataAccessObject,
                              RegisterOutputBoundary registerOutputBoundary,
                              UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = registerOutputBoundary;
        this.userFactory = userFactory;
        this.APIRestaurantDataAccessObject = APIRestaurantDataAccessObject;
    }

    @Override
    public void execute(RegisterInputData registerInputData) {
        if (userDataAccessObject.existsByName(registerInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists, please use another username.");
        } else if (!registerInputData.getPassword().equals(registerInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            try{
                APIRestaurantDataAccessObject.getLocalRestaurants(registerInputData.getLocation());
                LocalDateTime now = LocalDateTime.now();
                // Generate a random 6 digits number using the helper function
                String userID = createUserID().toString();
                // Below are codes to check for bugs
                //System.out.println("This is register interactor. Here we create a user with ID " + userID +
                        //", username " + registerInputData.getUsername() +
                        //", password " + registerInputData.getPassword() +
                        //", location" + registerInputData.getLocation() +
                        //"and time " + now);
                User user = userFactory.create(userID, registerInputData.getUsername(), registerInputData.getPassword(),
                        registerInputData.getLocation(), now);
                userDataAccessObject.save(user);

                RegisterOutputData registerOutputData = new RegisterOutputData(user.getUsername(), now.toString(),false);
                userPresenter.prepareSuccessView(registerOutputData);
        } catch (Exception e) {
                userPresenter.prepareFailView("City currently not supported.");
            }
        }
    }

    private Integer createUserID() {
        Random random = new Random();
        int userID = 100000 + random.nextInt(900000);
        // Check if this random number is already taken
        if (!userDataAccessObject.duplicatedID(userID)) {
            return userID;
        }
        else {
            return createUserID();
        }
    }
}