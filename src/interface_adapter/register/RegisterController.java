package interface_adapter.register;

import use_case.register.RegisterInputBoundary;
import use_case.register.RegisterInputData;

public class RegisterController {
    final RegisterInputBoundary registerInteractor;
    public RegisterController(RegisterInputBoundary registerInteractor) {
        this.registerInteractor = registerInteractor;
    }

    public void execute(String username, String password1, String password2) {
        RegisterInputData registerInputData = new RegisterInputData(
                username, password1, password2);

        registerInteractor.execute(registerInputData);
    }
}
