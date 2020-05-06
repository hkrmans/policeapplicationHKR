package sample;

public class Account {
    private Person owner;
    private String username;
    private String password;
    private String email;

    public Account(Person owner, String username, String password, String email) {
        this.owner = owner;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Person getOwner() {
        return owner;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
