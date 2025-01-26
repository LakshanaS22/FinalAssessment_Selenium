package pages;

import locators.HomePageLocators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToWebsite(String url) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    public void enterLocation(String fromLocation, String toLocation) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement closeButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageLocators.CLOSE_BUTTON_XPATH)));
            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (TimeoutException e) {
        }

        WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.FROM_FIELD_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fromField);
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.FROM_INPUT_XPATH)));
        fromInput.sendKeys(fromLocation);

        WebElement fromSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.FROM_SUGGESTION_XPATH)));
        fromSuggestion.click();

        WebElement toField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.TO_FIELD_XPATH)));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toField);
        } catch (ElementClickInterceptedException e) {
            new Actions(driver).moveToElement(toField).click().perform();
        }
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.TO_INPUT_XPATH)));
        toInput.sendKeys(toLocation);

        WebElement toSuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.TO_SUGGESTION_XPATH)));
        toSuggestion.click();
    }

    public void enterDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        LocalDate today = LocalDate.now();
        int daysInMonth = today.lengthOfMonth();

        try {
            WebElement departureTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.DEPARTURE_TAB_XPATH)));
            departureTab.click();
            WebElement targetDate = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@aria-label='Thu Feb 06 2025']//p[@class='fsw__date'][normalize-space()='6']")
            ));
            targetDate.click();
            WebElement returnTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[@class='sc-12foipm-9 hInlKv']")
            ));

            wait.until(ExpectedConditions.elementToBeClickable(returnTab));
            returnTab.click();


//            WebElement currentDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.CURRENT_DATE_XPATH)));
//
//            int currentDay = Integer.parseInt(currentDate.getText());
//            System.out.println("Current selected day: " + currentDay);
//
//            int targetDay = currentDay + 10;
//
//            if (targetDay > daysInMonth) {
//                targetDay = targetDay - daysInMonth;
//                System.out.println("Target day exceeds current month. Adjusted to next month: " + targetDay);
//
//                WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.NEXT_MONTH_BUTTON_XPATH)));
//                nextMonthButton.click();
//            }
//
//            String targetDateXPath = String.format("//p[@class='fsw__date' and text()='%d']", targetDay);
//            WebElement targetDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(targetDateXPath)));
//            targetDate.click();
//            System.out.println("Clicked on the target date: " + targetDay);
//
//            WebElement returnTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.RETURN_TAB_XPATH)));
//            returnTab.click();
//
//            int returnDay = targetDay + 1;
//            if (returnDay > daysInMonth) {
//                returnDay = returnDay - daysInMonth;
//                WebElement nextMonthButtonForReturn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.NEXT_MONTH_BUTTON_XPATH)));
//                nextMonthButtonForReturn.click();
//            }
//            String returnDateXPath = String.format("//p[@class='fsw__date' and text()='%d']", returnDay);
//            WebElement returnDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(returnDateXPath)));
//            returnDate.click();
//            System.out.println("Clicked on the return date: " + returnDay);
//
//        } catch (TimeoutException e) {
//            System.out.println("Error: Element not found within the timeout.");
//            e.printStackTrace();
//        } catch (NoSuchElementException e) {
//            System.out.println("Error: No such element found.");
//            e.printStackTrace();
//        } catch (ElementClickInterceptedException e) {
//            System.out.println("Error: Element click intercepted.");
//            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
            e.printStackTrace();
        }
    }

    public void enterTravellerDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement travellers = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.TRAVELLERS_XPATH)));
        wait.until(ExpectedConditions.elementToBeClickable(travellers));

        travellers.click();

        WebElement child = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.CHILD_XPATH)));
        wait.until(ExpectedConditions.elementToBeClickable(child));

        child.click();

        WebElement doneButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePageLocators.DONE_BUTTON_XPATH)));

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        wait.until(ExpectedConditions.elementToBeClickable(doneButton));
        doneButton.click();

        try {
            WebElement searchFlightsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomePageLocators.SEARCH_FLIGHTS_BUTTON_XPATH)));

            searchFlightsButton.click();

        } catch (TimeoutException e) {
            System.out.println("Error: 'SEARCH FLIGHTS' button not found within the timeout.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while clicking the 'SEARCH FLIGHTS' button.");
            e.printStackTrace();
        }
    }
}