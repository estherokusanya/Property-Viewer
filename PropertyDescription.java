import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.io.IOException;
import java.lang.Exception;
import java.net.URISyntaxException;
import java.net.URI;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 * A class that Controls the functionality of the Property description window.
 * It involves favouriting of properties.

 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class PropertyDescription extends PropertiesWindow implements Initializable
{

    @FXML
    private Label name;

    @FXML
    private Label hostName;

    @FXML
    private Label neighbourhood;

    @FXML
    private Label roomType;

    @FXML
    private Label reviewsPerMonth;

    @FXML
    private Label hostListings;

    @FXML
    private Label avalability;

    @FXML
    private Label lastReview;

    @FXML
    private Label minNights;

    @FXML
    private Label reviews;

    @FXML
    private Hyperlink propertyLink;

    @FXML
    private Button favouriteButton;

    @FXML
    private ImageView star;

    /**
     *Initialize all the variables and sets labels.
     *
     * @param URL that will take in fxml file
     * @param ResourceBundle that will take in rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)

    {
        // Set all of the property description labels to the relevant values
        name.setText(getPropertyClicked().getName()); 
        hostName.setText(getPropertyClicked().getHost_name());
        neighbourhood.setText(getPropertyClicked().getNeighbourhood());
        roomType.setText(getPropertyClicked().getRoom_type());

        // If the property has no reviews per month change from the default value of -1.0 to displaying 0
        if (getPropertyClicked().getReviewsPerMonth()==-1.0)
        {
            reviewsPerMonth.setText("0");
        } 
        else
        {
            reviewsPerMonth.setText(""+ getPropertyClicked().getReviewsPerMonth());
        }

        hostListings.setText(""+getPropertyClicked().getCalculatedHostListingsCount());
        avalability.setText(""+getPropertyClicked().getAvailability365());
        lastReview.setText(""+getPropertyClicked().getLastReview());
        minNights.setText(""+getPropertyClicked().getMinimumNights());
        reviews.setText(""+getPropertyClicked().getNumberOfReviews());
        propertyLink.setText("http://www.google.com/maps/place/" + 
            getPropertyClicked().getLatitude()+","+getPropertyClicked().getLongitude());

        //Sets the status of the displayed property
        setFavouriteStatus();
    }

    /**
     * When the hyperlink is clicked, get the text and go to that link using the default browser on the desktop 
     */
    @FXML
    private void goToSite(MouseEvent event)
    {
        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI(propertyLink.getText()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            } 
        }
    }

    /**
     * Change favourite status when the favourite button is clicked
     * @param event (button click)
     */
    @FXML
    private void favouriteButtonClick(ActionEvent event)
    {

        if(getCurrentUser().loggedIn())
        {
            //Perform normal favourite functionality
            if(star.isVisible())
            {
                star.setVisible(false);
                favouriteButton.setText("Favourite");
                getCurrentUser().removeFromFav(getPropertyClicked());
            }
            else{
                star.setVisible(true);
                favouriteButton.setText("Unfavourite");
                getCurrentUser().addToFav(getPropertyClicked());
            }
        }else
        {
            //User cannot favourite properties
            notLoggedInMessage();
        }

    }
    /**
     * Sets the text on the favourite label and button according to whether or not
     * the property is the list of the users favourites.
     */
    @FXML
    private void setFavouriteStatus()
    {
        if(getCurrentUser().getFavouriteProperties().contains(getPropertyClicked()))
        {
            star.setVisible(true);
            favouriteButton.setText("Unfavourite");
        }else
        {
            star.setVisible(false);
            favouriteButton.setText("Favourite");
        }
    }

}
