package login;

import org.junit.Test;
import base.BaseTest;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.openqa.selenium.By;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    
    private boolean loggedIn = false;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.enterPasswordOfValidUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/admin";
        String actualUrl = driver.getCurrentUrl();        
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
        
        DashboardPage dashboardPage = new DashboardPage(driver);
        String expectedPanelHeadingText = "Dashboard";
        String actualPanelHeadingText = dashboardPage.getPanelHeadingText();
        assertTrue("Failed - panel heading texts doesn't match.", expectedPanelHeadingText.equals(actualPanelHeadingText));
        
        dashboardPage.logout();
        driver.get("http://bvtest.school.cubes.rs/login");
    }
    
    @Test
    public void testEmptyFieldsLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
        
        String expectedEmailMassage = "The email field is required.";
        String actualEmailMassage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match", expectedEmailMassage.equals(actualEmailMassage));      
    }
    
    @Test
    public void testInvalidEmailInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfNonExistingUser();
        loginPage.enterPasswordOfNonExistingUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
        
        String expectedEmailMassage = "These credentials do not match our records.";
        String actualEmailMassage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match", expectedEmailMassage.equals(actualEmailMassage));      
    }
    
    @Test
    public void testValidEmailInvalidPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.enterPasswordOfNonExistingUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
        
        String expectedEmailMassage = "These credentials do not match our records.";
        String actualEmailMassage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match", expectedEmailMassage.equals(actualEmailMassage));      
    }
    
    @Test
    public void testInvalidEmailValidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfNonExistingUser();
        loginPage.enterPasswordOfValidUser();
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
        
        String expectedEmailMassage = "These credentials do not match our records.";
        String actualEmailMassage = loginPage.getEmailMessageText();
        Assert.assertTrue("Failed - Email message's doesn't match", expectedEmailMassage.equals(actualEmailMassage));      
    }
    
    @Test 
    public void testCustomDataLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("hello@milomir.rs");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/login";
        String actualUrl = driver.getCurrentUrl();
        assertEquals("Url's doesn't match.", expectedUrl, actualUrl);
    }

    @Test
    public void testValidEmailEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailOfValidUser();
        loginPage.clickLoginButton();
        
        String expectedPasswordMessage = "The password field is required."; 
        String actualPasswordMessage = loginPage.getPasswordMessageText(); 
        Assert.assertTrue("Failed - Password message's doesn't match", expectedPasswordMessage.equals(actualPasswordMessage));
        
    }
    
}
