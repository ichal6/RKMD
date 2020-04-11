package Model;

public abstract class UserAbstract {
    protected String ID;
    protected String name;
    protected String surname;
    protected String login;
    protected String password;

    public UserAbstract(String[] dataAboutClient){
        ID = dataAboutClient[0];
        name = dataAboutClient[1];
        surname = dataAboutClient[2];
        login = dataAboutClient[3];
        password = dataAboutClient[4];
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String returnValue;
        returnValue = String.format("| %s | %s | %s | %s | %s |",
                this.getID(),
                this.getName(),
                this.getSurname(),
                this.getLogin(),
                this.getPassword());

        return returnValue;
    }

}
