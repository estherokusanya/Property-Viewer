import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.lang.Exception;
import javafx.scene.image.Image;

/**
 * Contoller of the Map Panel.
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class MapController extends ApplicationGUI implements Initializable
{
    @FXML
    private Label fromPriceLabel;
    @FXML
    private Label toPriceLabel;
    @FXML
    private Button logInOutButton;

    private HashMap<Polygon, String> shapeToName = new HashMap<Polygon, String>();

    @FXML 
    private Polygon barnBor;
    @FXML 
    private Polygon brenBor;
    @FXML 
    private Polygon hrgyBor;
    @FXML 
    private Polygon hackBor;
    @FXML 
    private Polygon waltBor;
    @FXML 
    private Polygon enfBor;
    @FXML 
    private Polygon isliBor;
    @FXML 
    private Polygon camdBor;
    @FXML 
    private Polygon wandBor;
    @FXML 
    private Polygon mertBor;
    @FXML 
    private Polygon cityBor;
    @FXML 
    private Polygon lewsBor;
    @FXML 
    private Polygon gwchBor;
    @FXML 
    private Polygon towhBor;
    @FXML 
    private Polygon sthwBor;
    @FXML 
    private Polygon lambBor;
    @FXML 
    private Polygon wstmBor;
    @FXML 
    private Polygon bexlBor;
    @FXML 
    private Polygon newhBor;
    @FXML 
    private Polygon barkBor;
    @FXML 
    private Polygon haveBor;
    @FXML 
    private Polygon redbBor;
    @FXML 
    private Polygon croyBor;
    @FXML 
    private Polygon bromBor;
    @FXML 
    private Polygon hillBor;
    @FXML 
    private Polygon suttBor;
    @FXML 
    private Polygon hammBor;
    @FXML 
    private Polygon hounBor;
    @FXML 
    private Polygon hrrwBor;
    @FXML 
    private Polygon richBor;
    @FXML
    private Polygon kingBor;
    @FXML
    private Polygon kensBor;
    @FXML
    private Polygon ealiBor;

    /**
     * Loads and color codes the map. 
     * Places price range selected on screen.
     * @param URL that will take in Statistics.fxml
     * @param ResourceBundle that will take in rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fromPriceLabel.setText("£" + getCurrentUser().getFromPrice());
        toPriceLabel.setText("£" + getCurrentUser().getToPrice());
        loadMap();
        colourChange();
        getCurrentUser().setLogInOutButton(logInOutButton);
    }

    /**
     * Counts the number of properties within the user's price range in each of the boroughs 
     * @param the borough. Each borough in the GUI is represented by a polygon (hence type Polygon)  
     * @return the number of properties in each borough that are within the price range
     */
    private int countOccurence(Polygon borough)
    {
        int counter = 0;
        String boroughName=shapeToName.get(borough);
        for (AirbnbListing property : getCurrentUser().getPropertiesInRange()){
            if(property.getNeighbourhood().equals(boroughName)){
                counter++;
            }
        }
        return counter;
    }

    /**
     * For each borough, the number of properties within the price range and the Polygon called borough is 
     * passed in the setColor() method
     */
    private void colourChange()
    {
        for (Polygon borough : shapeToName.keySet()){
            int count = countOccurence(borough); 
            setColor(count, borough);            

        }
    }

    /**
     * Depending on the number of properties, the colour of the polygon will change
     * @param how many properties are in the borough and are within the user's price range
     * @param the Polygon (the hexagonal shape)
     */
    private void setColor(int count, Polygon borough)
    {
        //between 0 and 200 properties
        if (count>0 && count<=200){
            borough.setFill(Color.color(0.662,0.941,0.619));
        }
        //between 200 and 500
        if(count>200 && count<=500){
            borough.setFill(Color.color(1.0,0.937,0.368));
        }
        //between 500 and 1000 properties
        if(count>500 && count<=1000){
            borough.setFill(Color.color(1.0,0.553,0.172));
        }
        //over 1000 properties
        if(count>1000){
            borough.setFill(Color.color(1.0,0.270,0.290));
        }
    }

    /**
     * A hashmap that contains the name of the shape as the key and the corresponding borough name as the value
     */
    private void loadMap()
    {
        shapeToName.put(barnBor, "Barnet");
        shapeToName.put(brenBor, "Brent");
        shapeToName.put(hrgyBor, "Haringey");
        shapeToName.put(hackBor, "Hackney");
        shapeToName.put(waltBor, "Waltham Forest");
        shapeToName.put(enfBor,  "Enfield");
        shapeToName.put(isliBor, "Islington");
        shapeToName.put(camdBor, "Camden");
        shapeToName.put(wandBor, "Wandsworth");
        shapeToName.put(mertBor, "Merton");
        shapeToName.put(cityBor, "City of London");
        shapeToName.put(lewsBor, "Lewisham");
        shapeToName.put(gwchBor, "Greenwich");
        shapeToName.put(towhBor, "Tower Hamlets");
        shapeToName.put(sthwBor, "Southwark");
        shapeToName.put(lambBor, "Lambeth");
        shapeToName.put(wstmBor, "Westminster");
        shapeToName.put(bexlBor, "Bexley");
        shapeToName.put(newhBor, "Newham");
        shapeToName.put(barkBor, "Barking and Dagenham");
        shapeToName.put(haveBor, "Havering");
        shapeToName.put(redbBor, "Redbridge");
        shapeToName.put(croyBor, "Croydon");
        shapeToName.put(bromBor, "Bromley");
        shapeToName.put(hillBor, "Hillingdon");
        shapeToName.put(suttBor, "Sutton");
        shapeToName.put(hammBor, "Hammersmith and Fulham");
        shapeToName.put(hounBor, "Hounslow");
        shapeToName.put(hrrwBor, "Harrow");
        shapeToName.put(richBor, "Richmond upon Thames");
        shapeToName.put(kingBor, "Kingston upon Thames");
        shapeToName.put(kensBor, "Kensington and Chelsea");
        shapeToName.put(ealiBor, "Ealing");
    }

    /**
     * When the borough is clicked, the property listing opens
     * @param the event - when the user clicks on the borough
     */
    @FXML
    private void boroughClick(MouseEvent event) throws Exception
    {
        getCurrentUser().setListToView("Borough");
        Polygon poly = (Polygon) event.getSource(); 
        getCurrentUser().setBoroughClicked(shapeToName.get(poly));
        try{
            URL url =getClass().getResource("PropertyListing.fxml"); 
            Pane root= FXMLLoader.load(url);
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(shapeToName.get(poly));
            stage.setScene(scene);
            Image image = new Image("property.png");
            stage.getIcons().add(image);
            stage.show();
        } 
        catch(IOException  e)
        {
            System.out.println("Error Opening fxml file");
        }
    }
}
