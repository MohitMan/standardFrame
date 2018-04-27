package com.disyy.standardFrame;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features",
glue = {"com.disyy.stepdefs"},
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html", "json:target/cucumber.json"})
public class CucumberTest {

}
