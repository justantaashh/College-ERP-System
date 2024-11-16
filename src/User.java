import java.util.*;

abstract class User implements IUserAuthenticable
{
    Scanner sc;
    protected String email;
    protected String password;

    public User() //default constructor
    {
        this.email = "";
        this.password = "";
    }
    public String getPass(){return this.password;}
    public String getEmail(){return this.email;}
    public User(String em, String pass)
    {
        this.email = em;
        this.password = pass;
    }

    public boolean verify_pass(String pass)
    {
        return this.password.equals(pass);
    }
    public abstract void displayMenu();
}

