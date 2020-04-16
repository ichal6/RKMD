package Model;

public class Builder {
    private String ID;
    private String name;
    private String surname;
    private String login;
    private String password;

    public Builder withID(String ID){
        this.ID = ID;
        return this;
    }
    public Builder withName(String name){
        this.name = name;
        return this;
    }
    public Builder withSurname(String surname){
        this.surname = surname;
        return this;
    }

    public Builder withLogin(String login){
        this.login = login;
        return this;
    }

    public Builder withPassword(String password){
        this.password = password;
        return this;
    }
    public User build(){
        return new User(this) {
        };
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

