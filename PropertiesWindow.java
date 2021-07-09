import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

/**
 * THe Controller for the Property Window Panel.
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class PropertiesWindow extends ApplicationGUI implements Initializable
{
    private ArrayList<AirbnbListing> propertyListDisplayed= new ArrayList<>();

    @FXML 
    private TableView<AirbnbListing> propertiesList;
    @FXML
    private TableColumn<AirbnbListing, String> colHostName;
    @FXML
    private TableColumn<AirbnbListing, Double> colPrice;
    @FXML
    private TableColumn<AirbnbListing, Integer> colReviews;
    @FXML
    private TableColumn<AirbnbListing, Integer> colMinNights; 

    private ObservableList<AirbnbListing> list=FXCollections.observableArrayList();

    @FXML
    private ComboBox sortBox;

    private static AirbnbListing propertyClicked;


    /**
     * Initialise all variables and loads table.
     * @param URL that will take in Statistics.fxml
     * @param ResourceBundle that will take in rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        loadTableView();
        colHostName.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("host_name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Double>("price"));
        colReviews.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Integer>("numberOfReviews"));
        colMinNights.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Integer>("minimumNights"));

    }

    /**
     * Load the list of the properties in the clicked borough into the table view Observable List
     */
    public void loadBoroughProperties()
    {
        ArrayList<AirbnbListing> list= getCurrentUser().getPropertiesInRange();
        String b = getCurrentUser().getBoroughClicked();
        for(AirbnbListing a: list)
        {
            if (a.getNeighbourhood().equals(b))
            {
                propertyListDisplayed.add(a);
            }
        }
    }

    /**
     * Load the list of the users favourite properties into the table view Observable List
     */
    public void loadFavouriteProperties()
    {
        for(AirbnbListing a: getCurrentUser().getFavouriteProperties())
        {
            propertyListDisplayed.add(a);
        }
    }

    /**
     * Loads the AirBnB Listing in the TableView
     */
    private void loadTableView()

    {
        if(getCurrentUser().getListToView().equals("Favourites"))
        {
            loadFavouriteProperties();
        } else 
        {
            loadBoroughProperties();
        }        

        for(AirbnbListing a: propertyListDisplayed)

        { 
            list.addAll(a);
        }
        propertiesList.getItems().addAll(list);
    }

    /**
     * Opens a window showing the description of the property
     * @param an event - when the user clicks on the property
     */
    @FXML
    private void propertyClicked(MouseEvent event)
    {
        AirbnbListing  property= propertiesList.getSelectionModel().getSelectedItem();
        setPropertyClicked(property);
        try{
            URL url =getClass().getResource("PropertyDescription.fxml"); 
            Pane root= FXMLLoader.load(url);
            Stage stage=new Stage();
            // JavaFX must have a Scene (window content) inside a Stage (window)
            Scene scene = new Scene(root);
            stage.setTitle("Property Description");
            stage.setScene(scene);
            // Show the Stage (window)
            stage.show();
        } 
        catch(IOException e)
        {
            System.out.println("Error Opening fxml file");
        }
    }

    /**
     * Sorts the columns, either by ascending or descending order, for price, number of reviews and host name
     * @param an event - when the user clicks on one of the columns to sort
     */
    @FXML
    private void sortBy(ActionEvent event)
    {
        String userChoice = sortBox.getValue().toString();

        switch(userChoice){
            case "Price: Ascending":
            propertiesList.getSortOrder().add(colPrice);
            colPrice.setSortType(TableColumn.SortType.ASCENDING);
            propertiesList.sort();
            propertiesList.getSortOrder().remove(colPrice);
            break;
            case "Price: Descending":
            propertiesList.getSortOrder().add(colPrice);
            colPrice.setSortType(TableColumn.SortType.DESCENDING);
            propertiesList.sort();
            propertiesList.getSortOrder().remove(colPrice);
            break;
            case "Number of Reviews":
            propertiesList.getSortOrder().add(colReviews);
            colReviews.setSortType(TableColumn.SortType.ASCENDING);
            propertiesList.sort();
            propertiesList.getSortOrder().remove(colReviews);
            break;
            case "Host Name":
            colHostName.setSortType(TableColumn.SortType.ASCENDING);
            propertiesList.getSortOrder().add(colHostName);
            propertiesList.sort();
            propertiesList.getSortOrder().remove(colHostName);
            break;
        }
    }

    /**
     * The property that is clicked by the user
     * @param a property of type AirbnbListing
     */
    protected void setPropertyClicked(AirbnbListing property)
    {
        this.propertyClicked=property;
    }

    /**
     * Accessor method that returns the value of propertyClicked
     * @returns the property that has been clicked (propertyClicked())
     */
    protected AirbnbListing getPropertyClicked()
    {
        return propertyClicked;
    }

}
