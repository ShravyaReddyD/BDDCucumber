package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions" }, monochrome = true, plugin = {
		"pretty", "html:target/cucumber-reports/htmlreport.html", "json:target/cucumber-reports/jsonreport.json",
		"junit:target/cucumber-reports/junitreport.xml" }, publish = true)

public class TestRunner {

}
