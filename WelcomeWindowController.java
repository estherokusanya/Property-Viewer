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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * The functionality for the welcomeWindow panel
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class WelcomeWindowController extends ApplicationGUI implements Initializable
{

    @FXML
    private ComboBox fromBox;
    @FXML
    private ComboBox toBox;
    @FXML
    private Label rangeErrorLabel;

    public int fromPrice;
    public int toPrice;

    @FXML
    private Label welcomeStatement;

    /**
     * Initialise all variables
     * @param URL that will take in welcomeWindow.fxml
     * @param ResourceBundle that will take in rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if(!(getCurrentUser().getName()==null)){
            welcomeStatement.setText("Welcome " + getCurrentUser().getName());
        }

    }

    /**
     * When the user chooses a range (toPrice and fromPrice), this method is invoked
     * @param event (on enter click)
     */
    @FXML
    private void onEnterClick(ActionEvent event) throws Exception
    {
        if (checkPrices())
        {
            getCurrentUser().setVariables(fromPrice, toPrice);
            getCurrentUser().setPropertiesInRange();
            toMapPane(event); 
        }
    }

    @FXML
    private void fromPriceSelect(ActionEvent event) throws Exception
    {
        fromPrice = Integer.parseInt(fromBox.getValue().toString().replace("£", ""));
    }

    @FXML 
    private void toPriceSelect(ActionEvent event) throws Exception
    {
        toPrice = Integer.parseInt(toBox.getValue().toString().replace("£", ""));
    }

    /**
     * Checks to see whether user input is valid
     * @return true is input is valid, false if input is not valid (i.e. executes the 'if' or 'else if' branch)
     */
    private boolean checkPrices()
    {
        if(fromPrice==0 && toPrice==0){
            rangeErrorLabel.setText("Error: Select a price range");
            return false;
        }

        else if (fromPrice>toPrice){
            rangeErrorLabel.setText("Error: Your from price is greater than the to price");
            return false;
        }

        else{
            return true;
        }
    }

    /**
     * Sets the welcome statement to the greeting plus the text passed into the parameter
     * @param text (the name)
     */
    public void setNameInTitle(String text)
    {
        welcomeStatement.setText(text);
    }

}

