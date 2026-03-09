package Session02.Ex3;

class User implements Authenticatable {

    private final String password;

    public User(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }
}