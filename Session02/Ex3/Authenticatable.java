package Session02.Ex3;

@FunctionalInterface
public interface Authenticatable {
    String getPassword();

    default boolean isAuthenticated() {
        return getPassword() != null && getPassword().isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword.hashCode();
    }
}
