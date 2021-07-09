import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Node; 
import javafx.scene.control.Button;
import javafx.scene.control.Label; 
import javafx.scene.layout.Pane;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The functionality for the Statistics panel, including the eight statistics displayed
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class StatisticsController extends ApplicationGUI implements Initializable 
{

    private ArrayList <AirbnbListing> data;
    private HashMap<String, HashSet<String>> assignneighbourhoodName = new HashMap<String,HashSet<String>>();
    private HashMap<Integer, Boolean> isStatVisible = new HashMap<Integer, Boolean>();
    private HashMap<Integer, String> statDescription = new HashMap<Integer, String>();

    @FXML
    private Label fromLabel;
    @FXML
    private Label toLabel;
    @FXML
    private Label descriptionLabel1;     
    @FXML
    private Label descriptionLabel2; 
    @FXML
    private Label descriptionLabel3; 
    @FXML
    private Label descriptionLabel4; 
    @FXML
    private Label resultLabel1; 
    @FXML
    private Label resultLabel2; 
    @FXML
    private Label resultLabel3; 
    @FXML
    private Label resultLabel4; 
    @FXML
    private Button logInOutButton;

    /**
     * Initialise all variables
     * @param URL that will take in Statistics.fxml
     * @param ResourceBundle that will take in rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        data = getCurrentUser().getPropertiesInRange();

        isStatVisible.put(0,false);
        isStatVisible.put(1,false);
        isStatVisible.put(2,false);
        isStatVisible.put(3,false);
        isStatVisible.put(4,false);
        isStatVisible.put(5,false);
        isStatVisible.put(6,false);
        isStatVisible.put(7,false);   

        statDescription.put(0, "Average Number of Reviews"); 
        statDescription.put(1, "Total Number of Available Properties");
        statDescription.put(2, "Number Of Entire Homes Or Apartments");
        statDescription.put(3, "Most Expensive Borough");
        statDescription.put(4, "Most prestigious flats in London");
        statDescription.put(5, "Host with the most reviews");
        statDescription.put(6, "Cheapest and Most Expensive property");
        statDescription.put(7, "Borough With Lowest Property Price (on average)");

        initialiselabel(descriptionLabel1, resultLabel1);
        initialiselabel(descriptionLabel2, resultLabel2);
        initialiselabel(descriptionLabel3, resultLabel3);
        initialiselabel(descriptionLabel4, resultLabel4);

        getCurrentUser().setLogInOutButton(logInOutButton);

        fromLabel.setText("£" + getCurrentUser().getFromPrice());
        toLabel.setText("£" + getCurrentUser().getToPrice());
    }

    /**
     * Switch-Case where each of the numbers (0-7) represents one of the eight statistics
     * @param an integer that will determine which one of the methods will be invoked
     * @return the name of the method (a String)
     */
    private String numberToStatistics(int number)
    { 
        String methodName = "";
        switch(number){
            case 0:
            methodName = "" + averageNumberOfReviews();
            break;
            case 1:
            methodName = "" +totalNumberOfAvailableProperties();
            break;
            case 2:
            methodName = "" +numberOfHomesOrApartments();
            break;
            case 3:
            methodName = "" +mostExpensiveBorough();
            break;
            case 4:
            methodName = "" +mostPrestigiousFlats();
            break;
            case 5:
            methodName = "" +hostWithMostReviews();
            break;
            case 6:
            methodName = "" +cheapestandMostExpensiveProperty();
            break;
            case 7:
            methodName = "" +AverageLowestPropertyPrice();
            break;
        }
        return methodName; 
    }

    /**
     * Loops through the entire data set and counts the total number of reviews, 
     * then divides the reviews by that size of the data to find the average
     * @return the average number of reviews to one decimal place
     */
    private double averageNumberOfReviews()
    {
        double NumberOfReviews = 0.0;
        for(int i = 0; i<data.size(); i++){
            NumberOfReviews += data.get(i).getNumberOfReviews();
        }
        return Math.round(NumberOfReviews / data.size()* 10)/10.0;
    }

    /**
     * counts the number of properties that are available (i.e. availability365 must not be 0)
     * @return the total number of properties that are available
     */
    private int totalNumberOfAvailableProperties()
    {
        int numberOfProperties = 0;
        int priceOfProperty = 0;
        for(int i = 0; i<data.size(); i++){
            if(data.get(i).getAvailability365() >0){ 
                numberOfProperties = numberOfProperties + 1;
            }
        }
        return numberOfProperties;
    }

    /**
     * Counts all the properties that have a room type of "Entire home/apt"
     * @return the number of properties with a room type of "Entire home/apt" in the data set
     */
    private int numberOfHomesOrApartments()
    {
        int numberOfHomeOrApt = 0;
        for(int i = 0; i<data.size(); i++){
            if(data.get(i).getRoom_type().contains("Entire home/apt")){
                numberOfHomeOrApt = numberOfHomeOrApt + 1;
            }
        }
        return numberOfHomeOrApt;
    }

    /**
     * Adds up all property prices for each borough and finds the borough with the highest total property price
     * @return the name of the borough with the highest total property price
     */
    private String mostExpensiveBorough()
    {     
        HashMap<String, Integer> boroughPrice = new HashMap<String, Integer>();
        for(int i = 0; i<data.size(); i++){
            int totalPrice = data.get(i).getPrice() * data.get(i).getMinimumNights(); 
            if(boroughPrice.containsKey(data.get(i).getNeighbourhood())){
                boroughPrice.put(data.get(i).getNeighbourhood(), totalPrice + boroughPrice.get(data.get(i).getNeighbourhood()));
            }
            else{
                boroughPrice.put(data.get(i).getNeighbourhood(), totalPrice);
            }
        }
        int maximumPrice = Integer.MIN_VALUE;
        String borough = "";
        for(String key: boroughPrice.keySet()){
            if(boroughPrice.get(key)> maximumPrice){
                borough = key;
                maximumPrice = boroughPrice.get(key);
            }
        }
        return borough;        
    }

    /**
     * returns the name and neighbourhood with the word 'prestigious' and 'flat' in their name
     * @return the name and borough of the properties that have the word 'flat' and 'prestigious' in their names
     */
    private String mostPrestigiousFlats()
    {
        for(int i = 0; i<data.size(); i++){       
            addNeighbourhoodName(data.get(i).getName(), data.get(i).getNeighbourhood());            
        }
        String result = "";
        for(String s: assignneighbourhoodName.keySet()) {
            String resulting = s.toLowerCase();
            if(resulting.contains("prestigious") && resulting.contains("flat") ){
                result +=s + assignneighbourhoodName.get(s) + "\n";
            }            
        }
        return result;
    }

    /**
     * Group all the neighbourhoods together
     * @param the borough of the property from the data set
     * @param the name of the property from the data set
     * @return void
     */
    private void addNeighbourhoodName(String param1, String param2)
    {
        assignneighbourhoodName.putIfAbsent(param1, new HashSet<String>());
        HashSet<String> nameOfProperty = this.assignneighbourhoodName.get(param1);
        nameOfProperty.add(param2);
        assignneighbourhoodName.put(param1,nameOfProperty);
    }

    /**
     * Finds the name of the host which, when all their reviews on their properties are combined, has the highest total number of reviews
     * @return a the name of the host with the most reviews on all of their properties combined
     */
    private String hostWithMostReviews()
    {
        HashMap<String, String> hostIDtoHostName = new HashMap<String, String>();
        HashMap<String, Integer> hostIDandReviews = new HashMap<String, Integer>();
        for(int i = 0; i<data.size(); i++){
            hostIDandReviews.putIfAbsent(data.get(i).getHost_id(), 0);
            hostIDandReviews.put(data.get(i).getHost_id(), data.get(i).getNumberOfReviews() + hostIDandReviews.get(data.get(i).getHost_id()));
            hostIDtoHostName.putIfAbsent(data.get(i).getHost_id(), data.get(i).getHost_name());
        }
        int max = Integer.MIN_VALUE;
        String theHostID = "";
        for(String key: hostIDandReviews.keySet()){
            if(hostIDandReviews.get(key)> max){
                theHostID = key;
            }
        }
        return hostIDtoHostName.get(theHostID);
    }

    /**
     * Finds the cheapest and the most expensive property within the user's price range
     * @return the name of the cheapest and most expensive property within the user's price range
     */
    private String cheapestandMostExpensiveProperty()
    {
        HashMap<String, Integer> propertyPrice = new HashMap<String, Integer>();
        HashMap<String, Integer> propertyCount = new HashMap<String, Integer>();
        for(int i = 0; i<data.size(); i++){
            propertyPrice.put(data.get(i).getName(), data.get(i).getPrice());
        }
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        String expensiveProperty = "";
        String cheapestProperty = "";
        for(String key: propertyPrice.keySet()){
            if(propertyPrice.get(key)> min){
                expensiveProperty = key;
                min = propertyPrice.get(key);
            }
        }
        for(String thekey: propertyPrice.keySet()){
            if(propertyPrice.get(thekey)< max){
                cheapestProperty = thekey;
                max = propertyPrice.get(thekey);
            }
        }
        return "most expensive property: " + expensiveProperty + "\n" + "cheapest property: " + cheapestProperty;
    }

    /**
     * Finds the neighbourhood with the lowest average property price
     * @return the neighbourhood with the lowest average property price
     */
    private String AverageLowestPropertyPrice()
    {
        HashMap<String, Integer> AverageBoroughPrice = new HashMap<String, Integer>();
        HashMap<String, Integer> AverageBoroughCount = new HashMap<String, Integer>();
        {         
            for(int i = 0; i<data.size(); i++){
                int totalPrice = data.get(i).getPrice() * data.get(i).getMinimumNights(); 
                if(AverageBoroughPrice.containsKey(data.get(i).getNeighbourhood())){
                    AverageBoroughPrice.put(data.get(i).getNeighbourhood(), totalPrice + AverageBoroughPrice.get(data.get(i).getNeighbourhood()));
                    AverageBoroughCount.put(data.get(i).getNeighbourhood(), 1 + AverageBoroughCount.get(data.get(i).getNeighbourhood()));
                }
                else{
                    AverageBoroughPrice.put(data.get(i).getNeighbourhood(), totalPrice);
                    AverageBoroughCount.put(data.get(i).getNeighbourhood(), 1);
                }
            }
            int minAverage = Integer.MAX_VALUE;
            String borough = "";
            for(String key: AverageBoroughPrice.keySet()){
                if(AverageBoroughPrice.get(key) / AverageBoroughCount.get(key)< minAverage){
                    borough = key;
                    minAverage = AverageBoroughPrice.get(key) / AverageBoroughCount.get(key);
                }
            }
            return borough;
        }
    }

    /**
     * Sets the labels the the user will see for quadrant one
     * @param the event that triggers the intial labels to be set for this quadrant 
     */
    @FXML
    private void quadrantOne(ActionEvent event) throws Exception
    {
        setLabels(descriptionLabel1, resultLabel1);
    }

    /**
     * Sets the labels the the user will see for quadrant two
     * @param the event that triggers the intial labels to be set for this quadrant 
     */
    @FXML
    private void quadrantTwo(ActionEvent event) throws Exception
    {
        setLabels(descriptionLabel2, resultLabel2);
    }

    /**
     * Sets the labels the the user will see for quadrant three
     * @param the event that triggers the intial labels to be set for this quadrant 
     */
    @FXML
    private void quadrantThree(ActionEvent event) throws Exception
    {
        setLabels(descriptionLabel3, resultLabel3);
    }

    /**
     * Sets the labels the the user will see for quadrant four
     * @param the event that triggers the intial labels to be set for this quadrant 
     */
    @FXML
    private void quadrantFour(ActionEvent event) throws Exception
    {
        setLabels(descriptionLabel4, resultLabel4);
    }

    /**
     * Sets the text of both the description label and the result label
     * @param the description label (label1) and the result label (label2)
     */
    private void setLabels(Label label1, Label label2)
    {
        boolean changed = false;
        Random rand = new Random();
        while (!changed){
            int numtest = rand.nextInt(8);
            if(!isStatVisible.get(numtest)){
                //retrieve the label description
                String temp = label1.getText();
                //map the label description to the key
                int j = getKey(statDescription, temp);
                //place the key currently being shown as false
                isStatVisible.put(j, false);
                //set the labels to the information new key that's currently available
                label1.setText(statDescription.get(numtest));
                label2.setText(numberToStatistics(numtest));
                //map the new key used as true or occupied
                isStatVisible.put(numtest, true);
                changed=true;
            }
        }

    }

    /**
     * Initialises the description label (label1) and result label (label2) and sets the visibility to true 
     * @param the description label (label1) and result label (label2)
     */
    private void initialiselabel(Label label1, Label label2)
    {
        for(int i = 0; i < 8; i++){
            //if a statistic id is false ie it's not currently being shown
            if(!isStatVisible.get(i)){
                //set the labels to the information new key that's currently available
                label1.setText(statDescription.get(i));
                label2.setText(numberToStatistics(i));
                //map the new key used as true or occupied
                isStatVisible.put(i, true);
                return;
            }
        }
    }

    /**
     * checks to see if the second parameter (value) is equal to any of the values of the first parameter (map)
     * @param a hashmap that takes in an integer as the key and a String as a value
     * @param value (String)
     * @return i if the value equals to a value in the map, or 0 if not
     */
    private int getKey(HashMap <Integer, String> map, String value) {
        for (int i = 0; i<map.size(); i++) {
            if (value.equals(map.get(i))) {
                return i;
            }
        }
        return 0;
    } 

} 
