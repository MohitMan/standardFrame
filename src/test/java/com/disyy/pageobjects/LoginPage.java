package com.disyy.pageobjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.disyy.standardFrame.StandardMethods;

@Component
public class LoginPage extends StandardMethods {
	
	private By username = By.xpath("//input[@id='inputEmail']");
	private By password = By.xpath("//input[@id='inputPassword']");
	private By sign_in = By.xpath("//button[@id='submitButton']");
	
	public void navigateToUrl(String urlValue)
	{
		driver.get(urlValue);
	}
	public void setUsername(String usernameValue)
	{
		XPATH(username).sendKeys(usernameValue);
	}
	
	public void setPassword(String passwordValue)
	{
		XPATH(password).sendKeys(passwordValue);
	}
	
	public void clickSignin()
	{
		XPATH(sign_in).click();
	}
}
