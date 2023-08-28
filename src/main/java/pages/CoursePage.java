package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CoursePage extends AbstractOtusPage {
    private static final String DURATION = "M17.1604 2.76862L17.1614 3.51824C19.916 3.73413 21.7357 5.61119 21.7386 8.48975L21.7495 16.9155C21.7534 20.054 19.7817 21.985 16.6213 21.99L8.9014 22C5.76071 22.004 3.76433 20.027 3.76038 16.8795L3.74952 8.55272C3.74557 5.65517 5.50104 3.78311 8.25568 3.53024L8.2547 2.78061C8.25371 2.34083 8.57953 2.01 9.01395 2.01C9.44838 2.009 9.77419 2.33883 9.77518 2.77861L9.77617 3.47826L15.6409 3.47027L15.6399 2.77062C15.6389 2.33084 15.9647 2.001 16.3992 2C16.8237 1.999 17.1594 2.32884 17.1604 2.76862ZM5.27099 8.86157L20.2191 8.84158V8.49175C20.1767 6.34283 19.0985 5.21539 17.1634 5.04748L17.1643 5.81709C17.1643 6.24688 16.8296 6.5877 16.4051 6.5877C15.9707 6.5887 15.6439 6.24887 15.6439 5.81909L15.6429 5.0095L9.77814 5.01749L9.77913 5.82609C9.77913 6.25687 9.4543 6.5967 9.01987 6.5967C8.58545 6.5977 8.25865 6.25887 8.25865 5.82809L8.25766 5.05847C6.33237 5.25137 5.26704 6.38281 5.27 8.55072L5.27099 8.86157ZM15.9894 13.4043V13.4153C15.9993 13.8751 16.3745 14.2239 16.8296 14.2139C17.2739 14.2029 17.6284 13.8221 17.6185 13.3623C17.5978 12.9225 17.2414 12.5637 16.798 12.5647C16.3439 12.5747 15.9884 12.9445 15.9894 13.4043ZM16.805 17.892C16.3508 17.882 15.9845 17.5032 15.9835 17.0435C15.9736 16.5837 16.3379 16.2029 16.7921 16.1919H16.802C17.266 16.1919 17.6422 16.5707 17.6422 17.0405C17.6432 17.5102 17.268 17.891 16.805 17.892ZM11.9216 13.4203C11.9414 13.8801 12.3175 14.2389 12.7717 14.2189C13.216 14.1979 13.5705 13.8181 13.5507 13.3583C13.5399 12.9085 13.1746 12.5587 12.7303 12.5597C12.2761 12.5797 11.9206 12.9605 11.9216 13.4203ZM12.7757 17.8471C12.3215 17.8671 11.9463 17.5082 11.9256 17.0485C11.9256 16.5887 12.28 16.2089 12.7342 16.1879C13.1785 16.1869 13.5448 16.5367 13.5547 16.9855C13.5754 17.4463 13.22 17.8261 12.7757 17.8471ZM7.85384 13.4553C7.87359 13.915 8.24976 14.2749 8.70393 14.2539C9.14823 14.2339 9.50268 13.8531 9.48194 13.3933C9.47207 12.9435 9.10676 12.5937 8.66147 12.5947C8.2073 12.6147 7.85285 12.9955 7.85384 13.4553ZM8.70788 17.8521C8.25371 17.8731 7.87852 17.5132 7.85779 17.0535C7.8568 16.5937 8.21224 16.2129 8.66641 16.1929C9.11071 16.1919 9.47701 16.5417 9.48688 16.9915C9.50761 17.4513 9.15316 17.8321 8.70788 17.8521Z";
    private static final String FORMAT = "M3.90039 10.3178C3.90039 5.71789 7.74427 2 12.3938 2C17.0565 2 20.9004 5.71789 20.9004 10.3178C20.9004 12.6357 20.0574 14.7876 18.6699 16.6116C17.1392 18.6235 15.2525 20.3765 13.1289 21.7524C12.6429 22.0704 12.2043 22.0944 11.6708 21.7524C9.53513 20.3765 7.64848 18.6235 6.13089 16.6116C4.74237 14.7876 3.90039 12.6357 3.90039 10.3178ZM9.59462 10.5768C9.59462 12.1177 10.852 13.3297 12.3938 13.3297C13.9366 13.3297 15.2062 12.1177 15.2062 10.5768C15.2062 9.0478 13.9366 7.77683 12.3938 7.77683C10.852 7.77683 9.59462 9.0478 9.59462 10.5768Z";

    public CoursePage(WebDriver driver) {
        super(driver);
        logger = LogManager.getLogger(CoursePage.class);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'sc-s2pydo-6')]/h1")
    private WebElement courseTitleV1;
    @FindBy(xpath = "//div[@class = 'course-header2__title']")
    private WebElement courseTitleV2;
    @FindBy(xpath = "//h3[@class = 'sc-153sikp-7 jhjUZl']")
    private WebElement courseTitleV3;

    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='path' and @d='" + DURATION + "']/../../../../*[local-name()='p']")
    private WebElement courseDurationV1;
    @FindBy(xpath = "//p[contains(text(), 'Длительность обучения:')]/../../../div[@class = 'course-header2-bottom__content-item-text']/div/p")
    private WebElement courseDurationV2;
    @FindBy(xpath = "//div[contains(text(), 'Длительность:')]/../div[@class = 'sc-5ld6hn-10 hwjHWJ']")
    private WebElement courseDurationV3;

    @FindBy(xpath = "//*[local-name()='svg']//*[local-name()='path' and @d='" + FORMAT + "']/../../../../*[local-name()='p']")
    private WebElement courseFormatV1;
    @FindBy(xpath = "//p[contains(text(), 'Формат:')]/../../../div[@class = 'course-header2-bottom__content-item-text']/div/p")
    private WebElement courseFormatV2;
    @FindBy(xpath = "//div[contains(text(), 'Формат:')]/../div[@class = 'sc-5ld6hn-10 hwjHWJ']")
    private WebElement courseFormatV3;
    @FindBy(xpath = "//div[@class = 'course-process-description__content']")
    private WebElement courseFormatV4;

    @FindBy(xpath = "//div[@class = 'sc-x072mc-0 sc-1oat6ot-0 hOtCic dYVcmu']")
    private WebElement courseDescriptionV1;
    @FindBy(xpath = "//div[@class = 'sc-x072mc-0 sc-s2slrh-0 hOtCic jLsgNA']")
    private WebElement courseDescriptionV2;
    @FindBy(xpath = "//div[@class = 'course-about__content']")
    private WebElement courseDescriptionV3;
    @FindBy(xpath = "//div[@class = 'sc-153sikp-8 ctOEuT']")
    private WebElement courseDescriptionV4;


    private String tryTitle(WebElement we) {
        try {
            waitOne.until(ExpectedConditions.visibilityOf(we));
            return we.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        return null;
    }

    private String tryDuration(WebElement we) {
        try {
            waitOne.until(ExpectedConditions.visibilityOf(we));
            return we.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        return null;
    }

    private String tryFormat(WebElement we) {
        try {
            waitOne.until(ExpectedConditions.visibilityOf(we));
            return we.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        return null;
    }

    public String getTitle() {
        String title = null;
        try {
            title = tryTitle(courseTitleV1);
            if (title != null) {
                return title;
            }
            title = tryTitle(courseTitleV2);
            if (title != null) {
                return title;
            }
            title = tryTitle(courseTitleV3);
            if (title != null) {
                return title;
            }
        } finally {
            logger.info(String.format("Название курса: %s", title));
        }
        return null;
    }

    public boolean hasDuration() {
        String duration = null;
        try {
            duration = tryDuration(courseDurationV1);
            if (duration != null && !duration.isEmpty()) {
                return true;
            }
            duration = tryDuration(courseDurationV2);
            if (duration != null && !duration.isEmpty()) {
                return true;
            }
            duration = tryDuration(courseDurationV3);
            if (duration != null && !duration.isEmpty()) {
                return true;
            }
        } finally {
            logger.info(String.format("Продолжительность обучения: %s", duration));
        }

        return false;
    }

    public boolean hasFormat() {
        String format = null;
        try {
            format = tryFormat(courseFormatV1);
            if (format != null && !format.isEmpty()) {
                return true;
            }
            format = tryFormat(courseFormatV2);
            if (format != null && !format.isEmpty()) {
                return true;
            }
            format = tryFormat(courseFormatV3);
            if (format != null && !format.isEmpty()) {
                return true;
            }
            format = tryFormat(courseFormatV4);
            if (format != null && !format.isEmpty()) {
                return true;
            }
        } finally {
            logger.info(String.format("Формат обучения: %s", format));
        }
        return false;
    }

    public boolean hasDescription() {
        try {
            waitOne.until(ExpectedConditions.visibilityOf(courseDescriptionV1));
            return courseDescriptionV1.isDisplayed() && !courseDescriptionV1.getText().isEmpty();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        try {
            waitOne.until(ExpectedConditions.visibilityOf(courseDescriptionV2));
            return courseDescriptionV2.isDisplayed() && !courseDescriptionV2.getText().isEmpty();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        try {
            waitOne.until(ExpectedConditions.visibilityOf(courseDescriptionV3));
            return courseDescriptionV3.isDisplayed() && !courseDescriptionV3.getText().isEmpty();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        try {
            waitOne.until(ExpectedConditions.visibilityOf(courseDescriptionV4));
            return courseDescriptionV4.isDisplayed() && !courseDescriptionV4.getText().isEmpty();
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info(e);
        }
        return false;
    }
}
