import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 * This class holds all the user preferences that they've selected.
 * This is then used by all the other classes when displaying information
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class UserInfo
{
    //Variable to store the range of prices entered by the user.
    private static int fromPrice;
    private static int toPrice;
    
    //The first name of the user logged on
    private static String name;
    
    //The name of the borough that the user clicks
    private static String boroughClicked;
    
    //The list of the properties in the price range that the user selects 
    private static ArrayList<AirbnbListing> propertiesInRange=new ArrayList<>();
    
 
    //A list of the users favourite property listings
 
    private static ArrayList<AirbnbListing> userFavourites= new ArrayList<>();
    
    //The type of list the user wants to view
    private static String listToView;
    
    /**
     * Constructor for objects of class UserLogin
     */
    public UserInfo()
    {
        
    }

    protected void setVariables(int fromPrice, int toPrice)
    {
        this.fromPrice=fromPrice; 
        this.toPrice=toPrice;
    }
    
    /**
     * Accessor method that returns the entered From price.
     * @return fromPrice of integer type.
     */
    public int getFromPrice()
    {
        return fromPrice;
    }

    /**
     * Accessor method that returns the entered to price.
     * @return toPrice of integer type.
     */
    public int getToPrice()
    {
        return toPrice;
    }

    /**
     * Sets the name of the user for the application
     */
    public void setName(String name)
    {
        this.name=name;
    }

    /**
     * 
     * accessor method that returns the name of the user
     * @return name string
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Check whether or not a user is logged in
     * 
     * @return boolean True if they are logged in othrwise false.
     */
    public boolean loggedIn()
    { 
        boolean loggedIn=true;
        if (getName()==null) 
        {
            //logInOutButton.setText("Log In");
            loggedIn=false;
        }
        return loggedIn;
    }
    
    /**
     * Set the log out button text according to whether or not the user is logged in. 
     * The default is "Log out"
     */
    public void setLogInOutButton(Button button)
    {
        if(!loggedIn())
        {
            button.setText("Log In");
        }
    }
    
    /**
     * Creates an array list for all properties from the database belonging to the range of the rices entered by the user. 
     */
    public void setPropertiesInRange()
    {
        AirbnbDataLoader data=new AirbnbDataLoader();
        ArrayList<AirbnbListing> allProperties=data.load();
        propertiesInRange.clear();
        for(AirbnbListing p: allProperties)
        {
            if(p.getPrice()>=fromPrice && p.getPrice()<=toPrice)
            {
                propertiesInRange.add(p);
            }
        }
    }
    
    /**
     * Returns the array of properties in range.
     */
    public ArrayList<AirbnbListing> getPropertiesInRange()
    {
        return propertiesInRange;
    }
    
    /**
     * Sets a varaible to the name of the borough 
     * @param name Name of the Borough
     */
    public void setBoroughClicked(String name)
    {
        this.boroughClicked=name;
    }

    /**
     * accessor method that returns the name of the Borough Clicked.
     */
    public String getBoroughClicked()
    {
        return boroughClicked;
    }
    
    /**
     * Remove property to the users list of favourites.
     */    
    public void removeFromFav(AirbnbListing property)
    {
        for(int i=0; i<userFavourites.size(); i++)
        {
            if (userFavourites.get(i).equals(property))
            {
                userFavourites.remove(i);
            }
        }
    }

    /**
     * Add property to the users list of favourites.
     */ 
    public void addToFav(AirbnbListing property)
    {
        userFavourites.add(property);
    }

    /**
     * Return an array list of the users favourite properties.
     */ 
    public ArrayList<AirbnbListing> getFavouriteProperties()
    {
        return userFavourites;
    }

    /**
    * User cannot favourite properties Dialog
    * Set the value of the variable to store what list is to be viewed
    * @param type The type of list to be viewed ie. Favourites, Borough,...
    */
    public void setListToView(String type)
    {
        this.listToView=type;
    }
    
    /**
     * Accessor method to return the name of list to be viewed
     */
 
    public String getListToView()
    {
        return listToView;
    }
}
