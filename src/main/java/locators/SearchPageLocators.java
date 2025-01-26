package locators;

public class SearchPageLocators {
    public static final String DEPARTURE_BEFORE_6AM_FILTER_XPATH = "(//label[@id='depature_filter'])[1]";
    public static final String DEPARTURE_AFTER_6PM_FILTER_XPATH = "(//label[@id='depature_filter'])[9]";
    public static final String PRICE_SLIDER_HANDLE_XPATH = "//div[@class='fltSld-handle fltSld-handle-1']";
    public static final String MAX_PRICE_XPATH = "(//span[@class='dF alignItemsCenter']//svg/following-sibling::text())[2]";
    public static final String RETURN_PRICE_SLIDER_HANDLE_XPATH = "(//div[@class='fltSld-handle fltSld-handle-1'])[2]";
    public static final String RETURN_MAX_PRICE_XPATH = "(//span[@class='dF alignItemsCenter']//svg/following-sibling::text())[4]";
    public static final String DEPARTURE_FLIGHTS_XPATH = "//div[@class='departure-flights']//label[starts-with(@for,'01')]";
    public static final String RETURN_FLIGHTS_XPATH = "//div[@class='return-flights']//label[starts-with(@for,'02')]";
    public static final String BOOK_BUTTON_XPATH = "//input[@value='Book']";
    public static final String DEPARTURE_TAB_XPATH = "//li[@class='f500 font16 active']";
    public static final String RETURN_TAB_XPATH = "//li[@class='f500 font16']";
}
