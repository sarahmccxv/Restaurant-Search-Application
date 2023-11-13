package interface_adapter.register;

import use_case.register.RegisterInputBoundary;
import use_case.register.RegisterInputData;

public class RegisterController {
    final RegisterInputBoundary registerInteractor;
    public RegisterController(RegisterInputBoundary registerInteractor) {
        this.registerInteractor = registerInteractor;
    }

    public void execute(String username, String password1, String password2, String location) {
        RegisterInputData registerInputData = new RegisterInputData(
                username, password1, password2, location);
        // Below are codes to check for bugs
        //System.out.println("This is Register controller. Here we initiate a register input data" +
                //"where username name is " + username +
                //", password is " + password1 +
                //", location is " + location);

        registerInteractor.execute(registerInputData);
    }
}
