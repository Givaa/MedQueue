package presentazione;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewTest {

    @Test
    void showLoginView() {
        LoginView test=new LoginView();
        test.showLoginView();
    }
}