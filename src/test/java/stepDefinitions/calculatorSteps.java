package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class calculatorSteps {
	WebDriver driver = null;

	@Given("user navigates to the calculator page")
	public void user_navigates_to_the_calculator_page() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\shravyad\\eclipse-workspace\\CucumberFramework\\src\\test\\java\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
		driver.manage().window().maximize();
		// throw new io.cucumber.java.PendingException();
	}

	@When("^user enters (.*),(.*),(.*),(.*),(.*),(.*) and (.*)$")
	public void user_enters_annualincome_livingexpenses_homeloanrepayments_otherloanrepayments_othercommitments_and_creditcardlimits(
			int annualIncome, int otherIncome, int livingExpenses, int homeLoanRepayments, int otherLoanRepayments,
			int otherCommitments, int creditCardLimits) {

		driver.findElement(By.xpath("//input[@aria-labelledby = 'q2q1']")).sendKeys(String.valueOf(annualIncome));
		driver.findElement(By.xpath("//input[@aria-labelledby = 'q2q2']")).sendKeys(String.valueOf(otherIncome));
		driver.findElement(By.xpath("//input[@aria-labelledby = 'q3q1']")).sendKeys(String.valueOf(livingExpenses));
		driver.findElement(By.xpath("//input[@aria-labelledby = 'q3q3']"))
				.sendKeys(String.valueOf(otherLoanRepayments));
		driver.findElement(By.xpath("//input[@aria-labelledby = 'q3q5']")).sendKeys(String.valueOf(creditCardLimits));

	}

	@And("clicks on the borrow estimate button")
	public void clicks_on_the_borrow_estimate_button() {
		driver.findElement(By.id("btnBorrowCalculater")).click();
	}

	@Then("the borrowing estimate should be as expected")
	public void the_borrowing_estimate_should_be_as_expected() throws InterruptedException {

		Thread.sleep(3000);
		String actualBorrowingEstimate = driver.findElement(By.id("borrowResultTextAmount")).getText();
		String expectedBorrowingEstimate = "$482,000";

		Assert.assertEquals(expectedBorrowingEstimate, actualBorrowingEstimate);

	}

	@And("clicking the start over button should clear the form")
	public void clicking_the_start_over_button_should_clear_the_form() {
		driver.findElement(By.xpath("//div[@class='result__restart']/button")).click();
		String actualAmount = driver.findElement(By.id("borrowResultTextAmount")).getText();
		String expectedAmount = "$0";

		boolean amount = actualAmount.equals(expectedAmount);
		if (amount) {
			System.out.println("Succesfully form has cleared");
		} else {
			System.out.println("Form has not cleared");
		}

		driver.quit();
	}

}
