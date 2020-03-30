package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"C:\\Users\\NISCHAL\\eclipse-workspace\\restassured\\src\\test\\java\\features"},
plugin= {"json:target/jsonReports/cucumber-report.json"},glue= {"stepdefinitions"},tags= {"@AddPlace"})
public class TestRunner {

}
