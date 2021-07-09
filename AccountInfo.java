import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;
/**
 * Stores the accessor methods for all the account holder's information that was entered during sign up
 * this is including their firstname, surname, username and password
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class AccountInfo
{

    private String username;
    private String password;
    private String firstName;
    private String surname;

    /**
     * Constructor for objects of class AccountInfo
     * @param the firstname, surname, username and password, all as Strings
     */
    public AccountInfo(String fName, String surname, String username, String password)
    {
        this.username=username;
        this.password=password;
        this.firstName=fName;
        this.surname=surname;
    }

    /**
     * Accessor method that returns the firstname of the account holder/user
     * @return the firstname of the account holder/user
     */
    public String getName()
    {
        return firstName;
    }

    /**
     * Accessor method that returns the surname of the account holder/user
     * @return the surname of the account holder/user
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Accessor method that returns the username of the account holder/user
     * @return the username of the account holder/user
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Accessor method that returns the password of the account holder/user
     * @return the password of the account holder/user
     */
    public String getPassword()
    {
        return password;
    }

}
