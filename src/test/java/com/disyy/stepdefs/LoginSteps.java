package com.disyy.stepdefs;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.disyy.pageobjects.LoginPage;
import com.disyy.standardFrame.StandardMethods;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.disyy.standardFrame.SpringTest.class)
@SpringBootTest
public class LoginSteps {
	
	@Autowired
	private StandardMethods standardMethods;
	
	@Autowired
	LoginPage loginPage;
	
	@Value("${Login_Username}")
	String usernameValue;
	
	@Value("${Application.url}")
	String appUrl;
	
	@Value("${Login_Password}")
	String passwordValue;
	
	@Before
	public void before(Scenario s)
	{
		standardMethods.readfromexcel(s.getName());
		StandardMethods.driver = standardMethods.getdriver();
	}
	
	@After
	public void After(Scenario s) throws IOException
	{
		if(s.isFailed()) {
        	byte[] screenshot = ((TakesScreenshot)StandardMethods.driver).getScreenshotAs(OutputType.BYTES);
            s.embed(screenshot, "image/png");
        }
		StandardMethods.driver.quit();
        if (StandardMethods.driver1 != null)
        {
        	StandardMethods.driver1.quit();
        }
        Runtime.getRuntime().exec("taskkill \\f \\im chromedriver.exe");
        Runtime.getRuntime().exec("taskkill \\f \\im chrome.exe");
        StandardMethods.map.clear();
	}
	
	@Given("^User hit URL$")
	public void user_hit_URL() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println(appUrl);
		loginPage.navigateToUrl(appUrl);
	}

	@When("^User enter credentials to access application$")
	public void user_enter_credentials_to_access_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loginPage.setUsername(usernameValue);
	    loginPage.setPassword(passwordValue);
	    loginPage.clickSignin();
	}
}
