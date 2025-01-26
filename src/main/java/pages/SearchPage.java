package pages;

import locators.SearchPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setTimingFilter() {
        WebElement before6AMFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SearchPageLocators.DEPARTURE_BEFORE_6AM_FILTER_XPATH)));
        before6AMFilter.click();
        WebElement after6PMFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SearchPageLocators.DEPARTURE_AFTER_6PM_FILTER_XPATH)));
        after6PMFilter.click();
    }

    public void setPriceFilter(int difference){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceSliderHandle = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(SearchPageLocators.PRICE_SLIDER_HANDLE_XPATH)));

        WebElement maxPriceElement = driver.findElement(By.xpath(
                SearchPageLocators.MAX_PRICE_XPATH));

        int maxPrice = Integer.parseInt(maxPriceElement.getText().trim());
        int targetPrice = maxPrice - difference;

        adjustSlider(priceSliderHandle, maxPrice, targetPrice);

        WebElement returnPriceSliderHandle = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(SearchPageLocators.RETURN_PRICE_SLIDER_HANDLE_XPATH)));

        WebElement returnMaxPriceElement = driver.findElement(By.xpath(
                SearchPageLocators.RETURN_MAX_PRICE_XPATH));

        int returnMaxPrice = Integer.parseInt(returnMaxPriceElement.getText().trim());
        int returnTargetPrice = returnMaxPrice - difference;
        adjustSlider(returnPriceSliderHandle, returnMaxPrice, returnTargetPrice);
    }

    private void adjustSlider(WebElement sliderHandle, int maxPrice, int targetPrice) {
        Actions actions = new Actions(driver);

        double percentage = (double) (maxPrice - targetPrice) / maxPrice;

        int sliderWidth = sliderHandle.getSize().getWidth();
        int offsetX = (int) (percentage * sliderWidth);

        actions.clickAndHold(sliderHandle)
                .moveByOffset(-offsetX, 0)
                .release()
                .perform();
    }

    public void selectFlight(){
        String departureFlightsXPath = SearchPageLocators.DEPARTURE_FLIGHTS_XPATH;
        String returnFlightsXPath = SearchPageLocators.RETURN_FLIGHTS_XPATH;

        List<WebElement> departureFlights = driver.findElements(By.xpath(departureFlightsXPath));

        List<WebElement> returnFlights = driver.findElements(By.xpath(returnFlightsXPath));

        if (departureFlights.size() >= 2) {
            WebElement secondDepartureFlight = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[@for='012']")));
            secondDepartureFlight.click();
        } else if (departureFlights.size() > 0) {
            departureFlights.get(0).click();
        }

        if (returnFlights.size() >= 2) {
            WebElement secondReturnFlight = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[@for='021']")));
            secondReturnFlight.click();
        } else if (returnFlights.size() > 0) {
            returnFlights.get(0).click();
        }
    }

    public void bookFlight(String query){
        try {
            WebElement bookButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SearchPageLocators.BOOK_BUTTON_XPATH)));
            bookButton.click();
            System.out.println(query + " clicked successfully.");
        } catch (Exception e) {
            System.err.println("Unable to click the button: " + query + ". Error: " + e.getMessage());
        }
    }

    public void selectDepartureFare(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement departureTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SearchPageLocators.DEPARTURE_TAB_XPATH)));
            departureTab.click();
            System.out.println("Departure tab selected successfully.");
        } catch (Exception e) {
            System.err.println("Failed to select the Departure tab. Error: " + e.getMessage());
        }
    }

    public void selectReturnFare(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement returnTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SearchPageLocators.RETURN_TAB_XPATH)));
            returnTab.click();
            System.out.println("Return tab selected successfully.");
        } catch (Exception e) {
            System.err.println("Failed to select the Return tab. Error: " + e.getMessage());
        }
    }
}
