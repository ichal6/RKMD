package Model;

public class Admin extends UserAbstract {
    public Admin(String[] dataAboutClient) {
        super(dataAboutClient);
    }


    @Override
    public String toString() {
        String returnValue;
        returnValue = String.format("%s, %s, %s, %s, %s",
                this.getID(),
                this.getName(),
                this.getSurname(),
                this.getLogin(),
                this.getPassword());

        return returnValue;
    }
}

