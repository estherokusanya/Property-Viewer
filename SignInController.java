import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.net.URISyntaxException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * A class for controlling and maintain the sign in/ sign up panel.
 * A user can either sign up with a new username, or access his registered account by logging in
 * No two users will have the same username.
 *
 * @author Siduduziwe Mswabuki (20075458), Boluwatife Okusanya (20029923), Anya Kazi (20010243), Harnit Kaur Palra (20015786) 
 * @version 06.04.2021
 */
public class SignInController extends ApplicationGUI
{
    @FXML 
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    //Sign in 
    @FXML 
    private TextField siUsernameField;
    @FXML
    private PasswordField siPasswordField;

    //Sign up
    @FXML 
    private TextField nameField;
    @FXML
    private TextField surnameField;   
    @FXML 
    private TextField suUsernameField;
    @FXML
    private PasswordField suPasswordField; 

    //Sign up Error labels
    @FXML
    private Label suUsernameErrorLabel;
    @FXML
    private Label suPasswordErrorLabel;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label surnameErrorLabel;

    //Sign In Error Labels
    @FXML
    private Label siUsernameErrorLabel;
    @FXML
    private Label siPasswordErrorLabel;  

    /**
     * Accessor method the will return the username
     * @return a string that will return the username
     */
    public String getUsername()
    {
        return usernameField.getText();
    }

    /**
     * Accessor method the will return the password
     * @return a string that will return the password
     */
    public String getPassword()
    {
        return passwordField.getText();
    }

    /**
     * Functionality for the sign up button.
     * Checks if the username entered is unique.
     * @param event (button click)
     */
    @FXML
    private void signUpButtonClick(ActionEvent event) throws Exception
    {
        AccountInfoHandler accountInfo=new AccountInfoHandler();
        accountInfo.load();

        String u=suUsernameField.getText();

        if(suFieldPresenceCheck())
        {
            if (!accountInfo.userExists(u))
            {
                AccountInfo acc=new AccountInfo(nameField.getText(), surnameField.getText(), suUsernameField.getText(),suPasswordField.getText());
                accountInfo.csvWrite(acc);

                getCurrentUser().setName(nameField.getText());
                //to welcome window
                toWelcomeWindow(event);

            }
            else //username has been taken
            {
                suUsernameErrorLabel.setText("Username has been taken. Try a different username.");
            }
        }
    }

    /**
     * Funstionality for the sign in button.
     * Checks if the username and password match with those exixting in the accounts registered.
     * @param event (button click)
     */
    @FXML
    private void signInButtonClick(ActionEvent event) throws Exception
    {
        AccountInfoHandler accountInfo=new AccountInfoHandler();

        String u=siUsernameField.getText();
        String p=siPasswordField.getText();

        accountInfo.load();
        if(siFieldPresenceCheck(u, p))
        {

            if (accountInfo.userExists(u))
            {
                if(accountInfo.passwordMatch(u, p))
                {
                    //to welcome window
                    getCurrentUser().setName(accountInfo.getFirstName(u));
                    toWelcomeWindow(event);
                }
                else
                {
                    siPasswordErrorLabel.setText("Incorect Password! Try Again");
                }
            }
            else

            {
                invalidUsernameError();
            }
        }
    }

    /**
     * Method to check for empty fields while details are entered by the user.
     * @return true if neither of the fields are empty, false if any one of the fields are empty
     */
    private boolean suFieldPresenceCheck()

    {
        boolean check=true;
        if(nameField.getText().equals(""))
        {
            nameErrorLabel.setText("Enter Name");
            check=false;
        } else
        {
            nameErrorLabel.setText("");
        }

        if(surnameField.getText().equals(""))
        {
            surnameErrorLabel.setText("Enter Surname");
            check=false;
        } else
        {
            surnameErrorLabel.setText("");
        }

        if(suUsernameField.getText().equals(""))
        {
            suUsernameErrorLabel.setText("Enter Username");
            check=false;
        } else
        {
            suUsernameErrorLabel.setText("");
        }

        if(suPasswordField.getText().equals(""))
        {
            suPasswordErrorLabel.setText("Enter Password");
            check=false;
        } else
        {
            suPasswordErrorLabel.setText("");
        }

        return check;
    }

    /**
     * Method to check for empty fields while details are entered by the user.
     * @param user's username
     * @param user's password
     * @return true is the username or password is not empty, false if either one (or both) are empty
     */
    private boolean siFieldPresenceCheck(String username, String password)
    {
        boolean check=true;
        if(username.equals(""))
        {
            siUsernameErrorLabel.setText("Enter Username");
            check=false;
        } else
        {
            siUsernameErrorLabel.setText("");
        }

        if(password.equals(""))
        {
            siPasswordErrorLabel.setText("Enter Password");
            check=false;
        } else
        {
            siPasswordErrorLabel.setText("");
        }

        return check;
    }

    /**
     * Invalid Username Dialog
     */
    private void invalidUsernameError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Username");
        alert.setHeaderText("Your username is incorrect!");
        alert.setContentText("Check your spelling and Try again\nIf you are not a member, Sign Up");

        alert.showAndWait();
    }

}

