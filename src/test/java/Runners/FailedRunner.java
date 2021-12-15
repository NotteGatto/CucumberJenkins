package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed.txt",

        //glue is where we can find implementation for gherkin steps
        //here we provide the path of our steps package
        glue = "steps",

        //if we set dryRun = true (by default it's true), then no actual execution happens
        //it will quickly scan all gherkin steps(step definition) whether they have implementation or not
        //you just take copy of "snipped missed steps" and implement into "steps"
      //  dryRun = false,
        //it keeps console output for cucumber test easily readable
        //it will remove all the unreadable character
        monochrome = true,
       // tags = "@error",
        plugin = {"pretty"}
        //plugin - we use it to generate report for execution
        //pretty - it takes care of printing the steps in console
        //strict = true, alternative to dryRun, it checks all steps definition
)

public class FailedRunner {
}
