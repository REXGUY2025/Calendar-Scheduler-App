package app;

public class AuthService {

    public static boolean login(String username, String password) {
        return username.equals("REXGUY") && password.equals("1234");
    }
}
