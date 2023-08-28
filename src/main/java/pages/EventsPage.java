package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EventsPage extends AbstractOtusPage {
    public static final String[] MONTH = {

            "января", "февраля", "марта", "апреля",
            "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря"
    };
    public static final String[] M_NUMBER = {
            "01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12"
    };
    public static final String DATE_FORMAT = "dd MM";
    @FindBy(xpath = "//span[contains(@class, 'dod_new-event__calendar-icon')]/../span[@class = 'dod_new-event__date-text']")
    private List<WebElement> eventsList;
    @FindBy(xpath = "//div[contains(@class, 'dod_new-type__text')]")
    private List<WebElement> eventsTypes;
    @FindBy(xpath = "//div[contains(@class, 'dod_new-events-dropdown')]/div[@class = 'dod_new-events-dropdown__input']")
    private WebElement eventTypeSelector;
    @FindBy(xpath = "//div[contains(@class, 'dod_new-events-dropdown')]/div[@class = 'dod_new-events-dropdown__list js-dod_new_events-dropdown']/a[@class = 'dod_new-events-dropdown__list-item' and contains(text(), 'Открытый вебинар')]")
    private WebElement eventOpenWebinarSelector;

    public EventsPage(WebDriver driver) {
        super(driver);
        logger = LogManager.getLogger(EventsPage.class);
        PageFactory.initElements(driver, this);
    }

    private static String replaceAll(String src, String[] replace, String[] by) {
        for (int i = 0; i < replace.length; i++) {
            src = src.replace(replace[i], by[i]);
        }
        return src;
    }

    public void scrollDown() {
        long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        logger.info("Скролл страницы", lastHeight);
        while (true) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            new WebDriverWait(driver, Duration.ofSeconds(10));

            long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            logger.info("Скролл страницы", newHeight);
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }
    }

    public int eventsCount() {
        return eventsList.size();
    }

    public boolean checkDates() {
        Date date = java.util.Date.from(LocalDate.now().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        SimpleDateFormat ft = new SimpleDateFormat(DATE_FORMAT);
        for (WebElement el : eventsList) {
            Date parsingDate;
            try {
                parsingDate = ft.parse(replaceAll(el.getText(),
                        MONTH,
                        M_NUMBER));
                logger.info(parsingDate);
            } catch (ParseException e) {
                logger.info("Нельзя установить дату " + el.getText());
                return false;
            }
            if (date.compareTo(parsingDate) < 0) {
                return false;
            }
        }
        return true;
    }

    public void filterOpenWebinars() {
        waitTen.until(ExpectedConditions.visibilityOf(eventTypeSelector));
        eventTypeSelector.click();
        waitTen.until(ExpectedConditions.visibilityOf(eventOpenWebinarSelector));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", eventOpenWebinarSelector);
    }

    public boolean checkOpenWebinars(String type) {
        for (WebElement el : eventsTypes) {
            if (!type.equals(el.getText())) {
                return false;
            }
        }
        return true;
    }

}
