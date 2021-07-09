import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.util.ArrayList;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * The Superclass of all Panels. The Applications loads from here. 
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */

public class ApplicationGUI extends Application
{

    private UserInfo currentUser = new UserInfo();

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        URL url =getClass().getResource("SignInPanel.fxml"); 
        Pane root= FXMLLoader.load(url);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        //Add image icon to the title bar
        Image image = new Image("house.png");
        stage.getIcons().add(image);

        // Show the Stage (window)
        stage.show();
    }

    /**
     * Functionality of the button leading to the Map Panel
     */
    @FXML
    protected void toMapPane(ActionEvent event) throws Exception
    {
        URL url =getClass().getResource("MapPanel.fxml");
        Pane root= FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Get Stage Info
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Map");
        window.setScene(scene);
        window.show();

    }

    /**
     * Functionality of the button leading to the Statistics Panel
     */
    @FXML
    protected void toStatisticsPane(ActionEvent event) throws Exception
    {
        URL url =getClass().getResource("Statistics.fxml");
        Pane root= FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Get Stage Info
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Statistics");
        window.setScene(scene);
        window.show();

    }

    /**
     *Functionality of the button leading to the Welcome window Panel
     */
    @FXML
    public void toWelcomeWindow(Event event) throws Exception
    {
        URL url =getClass().getResource("welcomeWindow.fxml");
        Pane root= FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Get Stage Info
        Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Welcome");
        window.setScene(scene);
        window.show();

    }

    /**
     * Functionality of the button leading to the sign up Panel
     */
    @FXML
    protected void toSignUpWindow(MouseEvent e) throws Exception
    {
        URL url =getClass().getResource("SignUpPanel.fxml");
        Pane root= FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Get Stage Info
        Stage window= (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Sign Up");
        window.setScene(scene);
        window.show();

    }

    /**
     * Functionality of the button leading to the Sign in Panel
     */
    @FXML
    protected void toSignInWindow(MouseEvent e) throws Exception
    {
        URL url =getClass().getResource("SignInPanel.fxml");
        Pane root= FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Get Stage Info
        Stage window= (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(scene);
        window.show();

    }

    /**
     * Functionality of the login/logout buttons
     */
    @FXML
    protected void logInOutButtonClick(MouseEvent event) throws Exception
    {
        if (getCurrentUser().loggedIn())
        {
            logOutConfirm(event);
        }else
        {
            logInConfirm(event);
        }
    }

    /**
     * @return's an instance of the UserLogin class containing the User preferences
     */
    protected UserInfo getCurrentUser(){
        return currentUser;
    }

    /**
     * Leave page dialog. Confirms with the user before leaving the page.
     */
    private void logInConfirm(MouseEvent event) throws Exception
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Leave Page?");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Leave this page?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            toSignInWindow(event); 
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     * Log out Dialog. Confirms with the user before leaving the page.
     */
    private void logOutConfirm(MouseEvent event) throws Exception
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Log out Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            currentUser.setName(null);
            toSignInWindow(event);
            succesfulLogoutMessage();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }    

    /**
     * Logout message display.
     */
    private void succesfulLogoutMessage()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Successful Logout");
        alert.setHeaderText("You have successfully logged out!");
        alert.setContentText("Thank You for viewing properties with us x");

        alert.showAndWait();

    }

    /**
     * Functionality when the "View Favourites" button is clicked
     */
    @FXML
    public void viewFavClick(ActionEvent event) throws Exception
    {
        if(getCurrentUser().loggedIn())
        {
            getCurrentUser().setListToView("Favourites");
            try{
                URL url =getClass().getResource("PropertyListing.fxml"); 
                Pane root= FXMLLoader.load(url);
                Stage stage=new Stage();
                // JavaFX must have a Scene (window content) inside a Stage (window)
                Scene scene = new Scene(root);
                stage.setTitle("Favourite Properties");
                stage.setScene(scene);

                // Show the Stage (window)
                stage.show();
            } 
            catch(IOException e)
            {
                System.out.println("Error Opening fxml file");
            }
        }else
        {
            //User cannot have favourites
            notLoggedInMessage();
        }
    }

    /**
     * User cannot favourite properties Dialog
     */
    protected void notLoggedInMessage()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Not Logged In");
        alert.setHeaderText("Not Logged In");

        alert.setContentText("Login or Sign Up to add properties to favourites");

        alert.showAndWait();
    }

}
