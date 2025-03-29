package org.example.Testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\am575\\IdeaProjects\\OrangeHRM\\src\\Features",
        glue = "org/example/stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        publish = true

)

public class TestRunner {
}