package locators;

public class ReviewPageLocators {
    public static final String REVIEW_FLIGHT_NUMBER = "//div[@name='Review_Flight_Number']";
    public static final String REVIEW_ARRIVAL_DATE = "//span[@name='Review_Arrival_Date']/span[@class='f500']";
    public static final String REVIEW_ARRIVAL_TIME = "//div[@name='Review_Arrival_Time']";
    public static final String REVIEW_FLIGHTS_CLASS = "//span[@name='Review_Flights_Class']";
    public static final String REVIEW_FLIGHT_FAREFAMILY = "//span[@name='Review_Flight_Farefamily']";
    public static final String SRP_DETAILS_BAGGAGE_FLIGHTNO = "//div[@name='SRP_Details_Baggage_flightno']";
    public static final String BAGGAGE_TYPE = "//div[contains(text(), 'Baggage Type')]/following-sibling::div";
    public static final String CHECK_IN = "//span[text()='Check-In']";
    public static final String CABIN = "//span[text()='Cabin']";
    public static final String SECURE_MY_TRIP_RADIO_BUTTON = "//div[@class='selectInsurancestyles__RadioButton-sc-1xqzlcx-3 isMtbQ']";
    public static final String BILLING_SECTION = "//h2[div[text()='Your pincode and state']]";
    public static final String BILLING_ADDRESS_INPUT = "//input[@id='Billing Address']";
    public static final String PINCODE_INPUT = "//input[@id='Pincode']";
    public static final String STATE_DROPDOWN = "//div[@class='sc-SrznA iJQKxp']//p";
    public static final String STATE_OPTION_TEMPLATE = "//div[contains(text(), '%s')]";
    public static final String CONFIRM_CHECKBOX = "//input[@id='confirm_check']";
    public static final String GENDER_DROPDOWN = "//select[@name='GENDER_DROPDOWN']";
    public static final String GENDER_OPTION_TEMPLATE = "//select[@name='GENDER_DROPDOWN']/option[@value='%s']";
    public static final String FIRST_NAME_INPUT = "//input[@name='FIRST_NAME']";
    public static final String LAST_NAME_INPUT = "//input[@name='LAST_NAME']";
    public static final String DROPDOWN_IMAGE = "//img[@src='https://imgak.goibibo.com/flights-gi-assets/images/aerial/selectArw.webp']";
    public static final String GENDER_DROPDOWN2 = "//select[@name='GENDER_DROPDOWN']";
    public static final String GENDER_OPTION_TEMPLATE2 = "//select[@name='GENDER_DROPDOWN']/option[text()='%s']";
    public static final String FIRST_NAME_INPUT2 = "//input[@name='FIRST_NAME']";
    public static final String LAST_NAME_INPUT2 = "//input[@name='LAST_NAME']";
    public static final String EMAIL_FIELD = "//input[@name='EMAIL']";
    public static final String MOBILE_CODE_DROPDOWN = "//select[@name='COUNTRY_NAME']";
    public static final String MOBILE_NUMBER_FIELD = "//input[@name='MOBILE_NUMBER']";
    public static final String PROCEED_BUTTON_CONTACT = "//button[@class='dweb-commonstyles__FltBtn-sc-13fxsy5-12 bWfuav f500 wid25 font16']";
    public static final String SEAT_MAP = "//img[contains(@class, 'price-list-v2styles__SeatPriceElementImg')]";
    public static final String AVAILABLE_SEATS = "//img[contains(@src, 'dots4.png') or contains(@src, 'dots2.png') or contains(@src, 'circle.png')]";
    public static final String PROCEED_BUTTON_SEAT = "//button[text()='Proceed']";
    public static final String SKIP_TO_PAYMENT_BUTTON = "//button[contains(@class, 'addonPrcdBtn') and .//div[text()='Skip to Payment']]";
}
