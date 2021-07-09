import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.net.URISyntaxException;
import java.io.FileNotFoundException;

/**
 * Loads the account information from the CSV file
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class AccountInfoHandler
{

    private HashMap<String, String> userNandP=new HashMap();
    private ArrayList<AccountInfo> accounts = new ArrayList<AccountInfo>();

    /**
     * Constructor for objects of class AccountInfoLoader
     */
    public AccountInfoHandler()
    {

    }

    /**
     * Loads all the account information including firstname, surname, username and password 
     */
    public void load()
    {
        try{
            URL url = getClass().getResource("accounts.csv");
            CSVReader reader = new CSVReader(new FileReader(new File(url.toURI()).getAbsolutePath()));
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String first_name = line[0];
                String surname = line[1];
                String username = line[2];
                String password = line[3];
                AccountInfo account= new AccountInfo(first_name,surname,username, password);
                accounts.add(account);
                userNandP.put(username, password);
            }
        }
        catch(IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    /**
     * Write sign up account inomation to the csv file.
     * @param acc Account infomation 
     */
    public  void csvWrite(AccountInfo acc) 
    {
        try{
            URL url = getClass().getResource("accounts.csv");  
            File file= new File(url.toURI());

            FileWriter output = new FileWriter(file,true);
            CSVWriter write = new CSVWriter(output);

            String[] data1 = { acc.getName(), acc.getSurname(), acc.getUsername(), acc.getPassword()};
            write.writeNext(data1);
            write.close();

        } catch ( IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * returns the user's username and password
     * @return user's username and password
     */
    public HashMap<String, String> getUandP()
    {
        return userNandP;
    }

    /**
     * Checks whether the username exists in the accounts CSV file
     * @return true if the username does exist in the CSV file, false if it does not exist
     */
    public boolean userExists(String username)
    {
        return userNandP.containsKey(username);
    }

    /**
     * Checks whether the username matches the corresponding password stored on the CSV file
     * @return true if the password matches the username stored on the CSV file, false if it does not
     */
    public boolean passwordMatch(String username, String password)
    {
        return userNandP.get(username).equals(password);
    }

    /**
     * @return the firstname of the user, or null
     */
    public String getFirstName(String username)
    {
        for(AccountInfo anAccount : accounts)
        {
            if (username.equals(anAccount.getUsername())){
                return anAccount.getName();

            }
        }
        return null;
    }

}
