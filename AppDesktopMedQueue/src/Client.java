import presentazione.LoginView;
import presentazione.LoginInterface;

public class Client {

    public static void main (String[] args){
        LoginInterface medQueue= new LoginView();
        medQueue.showLoginView();

    }
}
