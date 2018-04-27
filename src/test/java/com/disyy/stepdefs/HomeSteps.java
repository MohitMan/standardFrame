package com.disyy.stepdefs;

import org.springframework.beans.factory.annotation.Autowired;

import com.disyy.pageobjects.HomePage;

import cucumber.api.java.en.Then;

public class HomeSteps {

	@Autowired
	HomePage homePage;
	
	@Then("^verify home page of application$")
	public void verify_home_page_of_application() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		homePage.validateHomeLogo();
	}
}
