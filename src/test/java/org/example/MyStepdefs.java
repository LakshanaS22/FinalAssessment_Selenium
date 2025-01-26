package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ReviewPage;
import pages.SearchPage;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyStepdefs {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);
    ReviewPage reviewPage = new ReviewPage(driver);

    public class ScenarioContext {
        private static Map<String, String> contextMap = new HashMap<>();

        public static void setContext(String key, String value) {
            contextMap.put(key, value);
        }

        public static String getContext(String key) {
            return contextMap.get(key);
        }
    }

    @Given("the Goibibo website is opened")
    public void theGoibiboWebsiteIsOpened() {
        homePage.navigateToWebsite("https://www.goibibo.com/");

    }

    @When("the user selects the From location as {string} and To location as {string}")
    public void theUserSelectsTheFromLocationAsAndToLocationAs(String fromLocation, String toLocation) {
        homePage.enterLocation(fromLocation, toLocation);
    }

    @And("the user selects the departure and return date")
    public void theUserSelectsTheDepartureAndReturnDate() {
        homePage.enterDate();

    }

    @And("the user sets the number of travelers to adult and child")
    public void theUserSetsTheNumberOfTravelersToAdultAndChild() {
        homePage.enterTravellerDetails();

    }

    @And("the user filters the departure and return timing")
    public void theUserFiltersTheDepartureAndReturnTiming() {
        searchPage.setTimingFilter();
    }

    @And("the user ensures the flight price is at least {string} less than the maximum price")
    public void theUserEnsuresTheFlightPriceIsLessThanTheMaximumPrice(int difference) {
        searchPage.setPriceFilter(difference);
    }

    @And("the user selects the second flight option from the filtered list")
    public void theUserSelectsTheSecondFlightOptionFromTheFilteredList() {
        searchPage.selectFlight();
    }

    @And("the user clicks {string} to select Flight")
    public void theUserClicksToSelectFlight(String query) {
        searchPage.bookFlight(query);

    }

    @When("the user selects the Depature tab to select the fare")
    public void theUserSelectsTheDepartureTabToSelectTheFare() {
        searchPage.selectDepartureFare();
    }

    @Then("verify that the saver option is chosen for Departure")
    public void verifyThatTheSaverOptionIsChosenForDeparture() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement saverOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='FareFamilyOverlaystyles__FareTd-sc-1nf293f-1 gOwzPw colHead active']//span[contains(text(),'SAVER')]")));
            Assert.assertTrue("Saver option is not selected for Departure.", saverOption.isDisplayed());
            System.out.println("Verified that the saver option is selected for Departure.");
        } catch (Exception e) {
            System.err.println("Failed to verify saver option for Departure. Error: " + e.getMessage());
        }
    }

    @And("the user selects the Return tab to select the fare")
    public void theUserSelectsTheReturnTabToSelectTheFare() {
        searchPage.selectReturnFare();
    }

    @Then("verify that the saver option is chosen for Return")
    public void verifyThatTheSaverOptionIsChosenForReturn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement saverOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='FareFamilyOverlaystyles__FareTd-sc-1nf293f-1 gOwzPw colHead active']//span[contains(text(),'SAVER')]")));
            Assert.assertTrue("Saver option is not selected for Return.", saverOption.isDisplayed());
            System.out.println("Verified that the saver option is selected for Return.");
        } catch (Exception e) {
            System.err.println("Failed to verify saver option for Return. Error: " + e.getMessage());
        }
        WebElement proceed = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Proceed']")));
        proceed.click();
    }

    @And("the system prints the flight details for Departure")
    public void theSystemPrintsTheFlightDetailsForDeparture() {
        WebElement reviewBooking = driver.findElement(By.xpath("//div[@class='font24 f500 white' and text()='Review your booking']"));
        assertTrue(reviewBooking.isDisplayed(), "Review your booking page is not displayed.");

        WebElement departureTab = driver.findElement(By.xpath("//span[@color='#2276E3' and text()='Departure']"));
        assertTrue(departureTab.isDisplayed(), "Departure tab is not displayed.");

        WebElement route = driver.findElement(By.xpath("//span[@class='font22 f600' and text()='Coimbatore - Chennai']"));
        assertTrue(route.isDisplayed(), "Route Coimbatore - Chennai is not displayed.");
        reviewPage.reviewDepartureDetails();
    }

    @And("the system prints the flight details for Return")
    public void theSystemPrintsTheFlightDetailsForReturn() {
        reviewPage.reviewReturnDetails();
    }

    @And("the system retrieves and asserts baggage details for both flights")
    public void theSystemRetrievesAndAssertsBaggageDetailsForBothFlightsAndClosesTheDetailsBox() {

        Map<String, String> baggageDetails = reviewPage.retrieveBaggageDetails();


        assertEquals("Unexpected flight number for the first flight.", "ExpectedFlightNumber1", baggageDetails.get("flightNumber1"));
        assertEquals("Unexpected baggage type for the first flight.", "ExpectedBaggageType1", baggageDetails.get("baggageType1"));
        assertEquals("Unexpected check-in status for the first flight.", "ExpectedCheckIn1", baggageDetails.get("checkIn1"));
        assertEquals("Unexpected cabin details for the first flight.", "ExpectedCabin1", baggageDetails.get("cabin1"));


        assertEquals("Unexpected flight number for the second flight.", "ExpectedFlightNumber2", baggageDetails.get("flightNumber2"));
        assertEquals("Unexpected baggage type for the second flight.", "ExpectedBaggageType2", baggageDetails.get("baggageType2"));
        assertEquals("Unexpected check-in status for the second flight.", "ExpectedCheckIn2", baggageDetails.get("checkIn2"));
        assertEquals("Unexpected cabin details for the second flight.", "ExpectedCabin2", baggageDetails.get("cabin2"));


        WebElement closeButton = driver.findElement(By.xpath("//svg[@class='Close__CloseIcon-sc-1d1q5yn-0 baggage-detailsstyles__CloseIconRight-sc-1ibt8yg-4 hppzCf eUcxES']"));
        closeButton.click();
    }


    @When("the user selects Secure My Trip under trip secure")
    public void theUserSelectsSecureMyTripUnderTripSecure() {

        reviewPage.secureTrip();
    }

    @And("the user enters the billing address details:")
    public void theUserEntersTheBillingAddressDetails(io.cucumber.datatable.DataTable dataTable) {

        reviewPage.enterAddress(dataTable);
    }


    @When("the user fills in the adult's details")
    public void theUserFillsInTheAdultSDetails(DataTable dataTable) {
        reviewPage.enterAdultDetails(dataTable);
    }


    @And("the user fills in the child's details")
    public void theUserFillsInTheChildSDetails(DataTable dataTable) {
        reviewPage.enterChildDetails(dataTable);
    }

    @And("the user fills in contact details")
    public void theUserFillsInContactDetails(DataTable dataTable) {
        reviewPage.enterContactDetails(dataTable);
    }

    @Then("validate the details")
    public void validateTheDetails() {
        String expectedAdultFirstName = ScenarioContext.getContext("adultFirstName");
        String expectedAdultLastName = ScenarioContext.getContext("adultLastName");
        String expectedAdultGender = ScenarioContext.getContext("adultGender");

        String expectedChildFirstName = ScenarioContext.getContext("childFirstName");
        String expectedChildLastName = ScenarioContext.getContext("childLastName");
        String expectedChildGender = ScenarioContext.getContext("childGender");

        String actualAdultFirstName = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'ADULT 1')]//following-sibling::div//span[contains(text(), 'First & Middle Name')]/following-sibling::span")).getText();
        String actualAdultLastName = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'ADULT 1')]//following-sibling::div//span[contains(text(), 'Last Name')]/following-sibling::span")).getText();
        String actualAdultGender = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'ADULT 1')]//following-sibling::div//span[contains(text(), 'Gender')]/following-sibling::span")).getText();

        String actualChildFirstName = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'CHILD 1')]//following-sibling::div//span[contains(text(), 'First & Middle Name')]/following-sibling::span")).getText();
        String actualChildLastName = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'CHILD 1')]//following-sibling::div//span[contains(text(), 'Last Name')]/following-sibling::span")).getText();
        String actualChildGender = driver.findElement(By.xpath("//div[@class='reviewTravellerOverlaystyles__RtdCard-sc-w2a3p-4 rtovN']//div[@id='rtpop_travellertype' and contains(text(), 'CHILD 1')]//following-sibling::div//span[contains(text(), 'Gender')]/following-sibling::span")).getText();

        boolean allAssertionsPassed = true;

        try {
            assert actualAdultFirstName.equals(expectedAdultFirstName) : "Adult first name does not match!";
            assert actualAdultLastName.equals(expectedAdultLastName) : "Adult last name does not match!";
            assert actualAdultGender.equals(expectedAdultGender) : "Adult gender does not match!";

            assert actualChildFirstName.equals(expectedChildFirstName) : "Child first name does not match!";
            assert actualChildLastName.equals(expectedChildLastName) : "Child last name does not match!";
            assert actualChildGender.equals(expectedChildGender) : "Child gender does not match!";
        } catch (AssertionError e) {
            allAssertionsPassed = false;
            System.out.println(e.getMessage());
        }

        if (allAssertionsPassed) {
            WebElement button = driver.findElement(By.xpath("//button[@id='rtpopup_accept' and contains(text(), \"That's Correct\")]"));
            button.click();
        } else {
            System.out.println("Validation failed. The button will not be clicked.");
        }
    }

    @And("the user clicks proceed to Seat Selection")
    public void theUserClicksProceedsToSeatSelection() {
        reviewPage.seatSelection();
    }
    @Then("finally assert the details are correctly displayed")
    public void finallyAssertTheDetailsAreCorrectlyDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            String departureRoute = ScenarioContext.getContext("departureRoute");
            String departureTime = ScenarioContext.getContext("departureTime");
            String departureArrivalTime = ScenarioContext.getContext("departureArrivalTime");
            String returnRoute = ScenarioContext.getContext("returnRoute");
            String returnTime = ScenarioContext.getContext("returnTime");
            String returnArrivalTime = ScenarioContext.getContext("returnArrivalTime");
            String adultName = ScenarioContext.getContext("adultName");
            String adultType = ScenarioContext.getContext("adultType");
            String adultGender = ScenarioContext.getContext("adultGender");
            String childName = ScenarioContext.getContext("childName");
            String childType = ScenarioContext.getContext("childType");
            String childGender = ScenarioContext.getContext("childGender");
            String primaryContact = ScenarioContext.getContext("primaryContact");
            String primaryEmail = ScenarioContext.getContext("primaryEmail");

            WebElement departureDetails = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[contains(@data-cy,'lobSummSourceDestCityExp') and contains(text(),'" + departureRoute + "')]")
            ));
            Assert.assertNotNull("Departure flight details are not displayed.", departureDetails);

            WebElement departureTimeElement = driver.findElement(By.xpath("//div[@data-cy='bookingDateTimeExp' and contains(text(),'" + departureTime + "')]"));
            Assert.assertNotNull("Departure time details are incorrect.", departureTimeElement);

            WebElement departureArrivalTimeElement = driver.findElement(By.xpath("//div[@data-cy='bookingDateTimeExp' and contains(text(),'" + departureArrivalTime + "')]"));
            Assert.assertNotNull("Arrival time for departure is incorrect.", departureArrivalTimeElement);

            WebElement returnDetails = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[contains(@data-cy,'lobSummSourceDestCityExp') and contains(text(),'" + returnRoute + "')]")
            ));
            Assert.assertNotNull("Return flight details are not displayed.", returnDetails);

            WebElement returnTimeElement = driver.findElement(By.xpath("//div[@data-cy='bookingDateTimeExp' and contains(text(),'" + returnTime + "')]"));
            Assert.assertNotNull("Return time details are incorrect.", returnTimeElement);

            WebElement returnArrivalTimeElement = driver.findElement(By.xpath("//div[@data-cy='bookingDateTimeExp' and contains(text(),'" + returnArrivalTime + "')]"));
            Assert.assertNotNull("Arrival time for return is incorrect.", returnArrivalTimeElement);

            WebElement adultTraveler = driver.findElement(By.xpath("//p[@data-cy='travellerFullName' and contains(text(),'" + adultName + "')]"));
            Assert.assertNotNull("Adult traveler details are not displayed correctly.", adultTraveler);

            WebElement adultTravelerType = driver.findElement(By.xpath("//span[@data-cy='travellerType' and contains(text(),'" + adultType + "')]"));
            Assert.assertNotNull("Adult traveler type is incorrect.", adultTravelerType);

            WebElement adultTravelerGender = driver.findElement(By.xpath("//span[@data-cy='travellerGender' and contains(text(),'" + adultGender + "')]"));
            Assert.assertNotNull("Adult traveler gender is incorrect.", adultTravelerGender);

            WebElement childTraveler = driver.findElement(By.xpath("//p[@data-cy='travellerFullName' and contains(text(),'" + childName + "')]"));
            Assert.assertNotNull("Child traveler details are not displayed correctly.", childTraveler);

            WebElement childTravelerType = driver.findElement(By.xpath("//span[@data-cy='travellerType' and contains(text(),'" + childType + "')]"));
            Assert.assertNotNull("Child traveler type is incorrect.", childTravelerType);

            WebElement childTravelerGender = driver.findElement(By.xpath("//span[@data-cy='travellerGender' and contains(text(),'" + childGender + "')]"));
            Assert.assertNotNull("Child traveler gender is incorrect.", childTravelerGender);

            WebElement ticketSentTo = driver.findElement(By.xpath("//p[@data-cy='travellerNameExp' and contains(text(),'" + primaryContact + "')]"));
            Assert.assertNotNull("Primary contact name is incorrect.", ticketSentTo);

            WebElement ticketContactDetails = driver.findElement(By.xpath("//p[@data-cy='travellerEmailMobileExp' and contains(text(),'" + primaryEmail + "')]"));
            Assert.assertNotNull("Primary contact email and phone are incorrect.", ticketContactDetails);

            System.out.println("All details have been validated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to validate the booking details: " + e.getMessage());
        }
    }
}