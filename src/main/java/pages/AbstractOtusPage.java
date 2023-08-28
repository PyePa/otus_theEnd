package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractOtusPage {
    final WebDriver driver;
    final WebDriverWait waitTen;
    final WebDriverWait waitOne;
    Logger logger;

    @FindBy(xpath = "//span[contains(text(), 'Посещая наш сайт, вы принимаете')]/../div/button")
    private WebElement cookieButton;
    @FindBy(xpath = "//jdiv[@class = 'closeIcon_a74e']")
    private WebElement chatButton;

    public AbstractOtusPage(WebDriver driver) {
        this.driver = driver;
        waitTen = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitOne = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public boolean pressCookieButton() {
        try {
            waitTen.until(ExpectedConditions.elementToBeClickable(cookieButton));

            try {
                if (cookieButton.isDisplayed()) {
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", cookieButton);
                    logger.info("Кнопка куки нажата");
                    return true;
                }
            } catch (NoSuchElementException ignored) {
            }
        } catch (TimeoutException ignored) {
        }
        return false;
    }

    public boolean pressChatButton() {
        try {
            waitTen.until(ExpectedConditions.elementToBeClickable(chatButton));

            try {
                if (chatButton.isDisplayed()) {
                    chatButton.click();
                    logger.info("Кнопка чат нажата");
                    return true;
                }
            } catch (NoSuchElementException ignored) {
            }
        } catch (TimeoutException ignored) {
        }
        return false;
    }
}
