package api.client;

public class Credentials {

    private final String email;
    private final String password;

    public Credentials(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public static Credentials fromUserDataAll(UserData user){
        return new Credentials(user.getEmail(), user.getPassword());
    }

    public static Credentials fromUserDataEmail(UserData user){
        return new Credentials(user.getEmail(),null);
    }

    public static Credentials fromUserDataPassword(UserData user){
        return new Credentials(null ,user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

