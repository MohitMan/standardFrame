package com.disyy.pageobjects;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.disyy.standardFrame.StandardMethods;

@Component
public class HomePage extends StandardMethods{

	private By homeLogo = By.xpath("//div[contains(text(),'AWS')]");
	
	public void validateHomeLogo()
	{
		WAIT("visible",homeLogo);
	}
}
