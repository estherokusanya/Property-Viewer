

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UserInfoTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UserInfoTest
{
    private UserInfo userInfo1;

    /**
     * Default constructor for test class UserInfoTest
     */
    public UserInfoTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        userInfo1 = new UserInfo();
        userInfo1.setVariables(100, 500);
        userInfo1.setName("Esther");
        userInfo1.setPropertiesInRange();
        userInfo1.setListToView("Favourites");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testSetVariables()
    {
        assertEquals(userInfo1.getFromPrice(), 100);
        assertEquals(userInfo1.getToPrice(),500);
    }
    

    @Test
    public void testSetName()
    {
        assertEquals(userInfo1.getName(), "Esther");
        
    }
    
    @Test
    public void testLoggedIn()
    {
        assertTrue(userInfo1.loggedIn());
    }
    
    @Test
    public void testPropertiesInRange()
    {
        assertTrue(userInfo1.getPropertiesInRange().get(0).getPrice()>=userInfo1.getFromPrice());
        assertTrue(userInfo1.getPropertiesInRange().get(0).getPrice()<=userInfo1.getToPrice());
    }
    
    @Test 
    public void testAddToFavourites()
    {
        int beforeSize=userInfo1.getFavouriteProperties().size();
        userInfo1.addToFav(userInfo1.getPropertiesInRange().get(0));
        assertTrue(beforeSize==userInfo1.getFavouriteProperties().size()-1);
        
    }
    
    @Test
    public void testRemoveFromFavourites()
    {
        int beforeSize=userInfo1.getFavouriteProperties().size();
        userInfo1.removeFromFav(userInfo1.getFavouriteProperties().get(0));
        assertTrue(beforeSize==userInfo1.getFavouriteProperties().size()+1);
    }
    
    @Test
    public void testSetListToView()
    {
        assertEquals(userInfo1.getListToView(), "Favourites");
    }
    
}


