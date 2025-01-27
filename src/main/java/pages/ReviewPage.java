package pages;

import io.cucumber.datatable.DataTable;
import locators.ReviewPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class ReviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void reviewDepartureDetails() {
        try {
            WebElement flightNameNumber = driver.findElement(By.xpath(ReviewPageLocators.REVIEW_FLIGHT_NUMBER));
            System.out.println("Flight Name and Number: " + flightNameNumber.getText());

            WebElement arrivalDate = driver.findElement(By.xpath(ReviewPageLocators.REVIEW_ARRIVAL_DATE));
            System.out.println("Arrive On (Date): " + arrivalDate.getText());

            WebElement arrivalTime = driver.findElement(By.xpath(ReviewPageLocators.REVIEW_ARRIVAL_TIME));
            System.out.println("Arrival Time: " + arrivalTime.getText());

            WebElement flightClass = driver.findElement(By.xpath(ReviewPageLocators.REVIEW_FLIGHTS_CLASS));
            System.out.println("Class: " + flightClass.getText());

            WebElement saver = driver.findElement(By.xpath(ReviewPageLocators.REVIEW_FLIGHT_FAREFAMILY));
            System.out.println("Fare Family: " + saver.getText());
        } catch (Exception e) {
            System.err.println("Error while extracting flight details: " + e.getMessage());
        }
    }

    public void reviewReturnDetails() {
        try {
            WebElement flightNameNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReviewPageLocators.REVIEW_FLIGHT_NUMBER)));
            WebElement arrivalDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReviewPageLocators.REVIEW_ARRIVAL_DATE)));
            WebElement departureTime = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReviewPageLocators.REVIEW_ARRIVAL_TIME)));
            WebElement flightClass = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReviewPageLocators.REVIEW_FLIGHTS_CLASS)));
            WebElement fareFamily = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReviewPageLocators.REVIEW_FLIGHT_FAREFAMILY)));

            System.out.println("Flight Name and Number: " + flightNameNumber.getText());
            System.out.println("Arrive On: " + arrivalDate.getText());
            System.out.println("Departure Time: " + departureTime.getText());
            System.out.println("Class: " + flightClass.getText());
            System.out.println("Fare Family: " + fareFamily.getText());
        } catch (Exception e) {
            System.err.println("Failed to extract flight details. Error: " + e.getMessage());
        }
    }

    public Map<String, String> retrieveBaggageDetails() {
        Map<String, String> baggageDetails = new HashMap<>();
        String flightNumber1 = driver.findElement(By.xpath(ReviewPageLocators.SRP_DETAILS_BAGGAGE_FLIGHTNO)).getText();
        String baggageType1 = driver.findElement(By.xpath(ReviewPageLocators.BAGGAGE_TYPE)).getText();
        String checkIn1 = driver.findElement(By.xpath(ReviewPageLocators.CHECK_IN)).getText();
        String cabin1 = driver.findElement(By.xpath(ReviewPageLocators.CABIN)).getText();

        baggageDetails.put("flightNumber1", flightNumber1);
        baggageDetails.put("baggageType1", baggageType1);
        baggageDetails.put("checkIn1", checkIn1);
        baggageDetails.put("cabin1", cabin1);

        String flightNumber2 = driver.findElement(By.xpath(ReviewPageLocators.SRP_DETAILS_BAGGAGE_FLIGHTNO)).getText();
        String baggageType2 = driver.findElement(By.xpath(ReviewPageLocators.BAGGAGE_TYPE)).getText();
        String checkIn2 = driver.findElement(By.xpath(ReviewPageLocators.CHECK_IN)).getText();
        String cabin2 = driver.findElement(By.xpath(ReviewPageLocators.CABIN)).getText();

        baggageDetails.put("flightNumber2", flightNumber2);
        baggageDetails.put("baggageType2", baggageType2);
        baggageDetails.put("checkIn2", checkIn2);
        baggageDetails.put("cabin2", cabin2);

        return baggageDetails;
    }

    public void secureTrip() {
        WebElement secureMyTripRadioButton = driver.findElement(By.xpath(ReviewPageLocators.SECURE_MY_TRIP_RADIO_BUTTON));
        secureMyTripRadioButton.click();
    }

    public void enterAddress(DataTable dataTable) {
        Map<String, String> billingDetails = dataTable.asMap(String.class, String.class);
        WebElement billingSection = driver.findElement(By.xpath(ReviewPageLocators.BILLING_SECTION));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", billingSection);

        WebElement addressInput = driver.findElement(By.xpath(ReviewPageLocators.BILLING_ADDRESS_INPUT));
        addressInput.clear();
        addressInput.sendKeys(billingDetails.get("Address"));

        WebElement pincodeInput = driver.findElement(By.xpath(ReviewPageLocators.PINCODE_INPUT));
        pincodeInput.clear();
        pincodeInput.sendKeys(billingDetails.get("Pincode"));

        WebElement stateDropdown = driver.findElement(By.xpath(ReviewPageLocators.STATE_DROPDOWN));
        stateDropdown.click();

        WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(ReviewPageLocators.STATE_OPTION_TEMPLATE, billingDetails.get("State")))));
        stateOption.click();

        WebElement confirmCheckbox = driver.findElement(By.xpath(ReviewPageLocators.CONFIRM_CHECKBOX));
        if (!confirmCheckbox.isSelected()) {
            confirmCheckbox.click();
        }
    }

    public void enterAdultDetails(DataTable dataTable) {
        Map<String, String> adultDetails = dataTable.asMap(String.class, String.class);
        String gender = adultDetails.get("Gender");
        String firstName = adultDetails.get("FirstName");
        String lastName = adultDetails.get("LastName");

        WebElement genderDropdown = driver.findElement(By.xpath(ReviewPageLocators.GENDER_DROPDOWN));
        genderDropdown.click();
        WebElement genderOption = driver.findElement(By.xpath(String.format(ReviewPageLocators.GENDER_OPTION_TEMPLATE, gender.toUpperCase())));
        genderOption.click();

        WebElement firstNameInput = driver.findElement(By.xpath(ReviewPageLocators.FIRST_NAME_INPUT));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.xpath(ReviewPageLocators.LAST_NAME_INPUT));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        ScenarioContext.setContext("adultGender", gender);
        ScenarioContext.setContext("adultFirstName", firstName);
        ScenarioContext.setContext("adultLastName", lastName);
    }

    public static class ScenarioContext {
        private static Map<String, String> contextMap = new HashMap<>();

        public static void setContext(String key, String value) {
            contextMap.put(key, value);
        }

        public static String getContext(String key) {
            return contextMap.get(key);
        }
    }
    public void enterChildDetails(DataTable dataTable) {
        Map<String, String> childDetails = dataTable.asMap(String.class, String.class);

        String gender = childDetails.get("Gender");
        String firstName = childDetails.get("FirstName");
        String lastName = childDetails.get("LastName");

        WebElement dropdownImage = driver.findElement(By.xpath(ReviewPageLocators.DROPDOWN_IMAGE));
        dropdownImage.click();

        WebElement genderDropdown = driver.findElement(By.xpath(ReviewPageLocators.GENDER_DROPDOWN2));
        genderDropdown.click();

        WebElement genderOption = driver.findElement(By.xpath(String.format(ReviewPageLocators.GENDER_OPTION_TEMPLATE2, gender)));
        genderOption.click();

        WebElement firstNameInput = driver.findElement(By.xpath(ReviewPageLocators.FIRST_NAME_INPUT2));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.xpath(ReviewPageLocators.LAST_NAME_INPUT2));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        ScenarioContext.setContext("childGender", gender);
        ScenarioContext.setContext("childFirstName", firstName);
        ScenarioContext.setContext("childLastName", lastName);
    }

    public void enterContactDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            String field = row.get("Field");
            String value = row.get("Value");

            if (field.equals("Email")) {
                WebElement emailField = driver.findElement(By.xpath(ReviewPageLocators.EMAIL_FIELD));
                emailField.sendKeys(value);
            }

            if (field.equals("Mobile code")) {
                WebElement mobileCodeDropdown = driver.findElement(By.xpath(ReviewPageLocators.MOBILE_CODE_DROPDOWN));
                Select select = new Select(mobileCodeDropdown);
                boolean isCodeSelected = false;
                for (WebElement option : select.getOptions()) {
                    if (option.getText().contains(value)) {
                        select.selectByVisibleText(option.getText());
                        isCodeSelected = true;
                        break;
                    }
                }
                if (!isCodeSelected) {
                    throw new RuntimeException("Country code " + value + " not found in the dropdown.");
                }
            }

            if (field.equals("MobileNumber")) {
                WebElement mobileNumberField = driver.findElement(By.xpath(ReviewPageLocators.MOBILE_NUMBER_FIELD));
                mobileNumberField.sendKeys(value);
            }
        }

        WebElement proceedButton = driver.findElement(By.xpath(ReviewPageLocators.PROCEED_BUTTON_CONTACT));
        proceedButton.click();
    }

    public void seatSelection() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    try {
        js.executeScript("window.scrollBy(0, 500)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReviewPageLocators.SEAT_MAP)));
        List<WebElement> allSeats = driver.findElements(By.xpath("//div[@class='seats-layout-v2styles__ToolTip-sc-usmql2-5']"));
        System.out.println("Total seats available for selection: " + allSeats.size());

        List<WebElement> availableSeats = new ArrayList<>();
        for (WebElement seat : allSeats) {
            WebElement seatImage = seat.findElement(By.tagName("img"));
            String seatImageSrc = seatImage.getAttribute("src");
            if (!seatImageSrc.contains("cross.png")) {
                availableSeats.add(seat);
            }
        }

        System.out.println("Unbooked seats available: " + availableSeats.size());

        if (availableSeats.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                WebElement seatToSelect = availableSeats.get(i);
                js.executeScript("arguments[0].scrollIntoView(true);", seatToSelect);
                wait.until(ExpectedConditions.elementToBeClickable(seatToSelect));
                seatToSelect.click();
                System.out.println("Selected seat: " + seatToSelect.getAttribute("id"));
            }
        } else {
            System.out.println("Not enough unbooked seats available for selection.");
        }
        WebElement proceedButton = driver.findElement(By.xpath(ReviewPageLocators.PROCEED_BUTTON_SEAT));
        js.executeScript("arguments[0].scrollIntoView(true);", proceedButton);
        wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
        proceedButton.click();
        WebElement skipToPaymentButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(ReviewPageLocators.SKIP_TO_PAYMENT_BUTTON)
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", skipToPaymentButton);
        skipToPaymentButton.click();
        System.out.println("Clicked 'Skip to Payment' button successfully.");
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("An error occurred during seat selection or skipping to payment.");
    }
}
}
