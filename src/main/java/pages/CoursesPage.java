package pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CoursesPage extends AbstractOtusPage {
    @FindBy(xpath = "//label[contains(text(), 'Тестирование')]")
    private WebElement testDirCheckbox;
    @FindBy(xpath = "//div[contains(text(), 'Каталог')]/../../../div/div/a")
    private List<WebElement> coursesList;
    @FindBy(xpath = "//button[contains(text(), 'Показать еще')]")
    private WebElement showMoreCoursesButton;

    public CoursesPage(WebDriver driver) {
        super(driver);
        logger = LogManager.getLogger(CoursesPage.class);
        PageFactory.initElements(driver, this);
    }

    public void clickTestDirCb() {
        waitTen.until(ExpectedConditions.visibilityOf(testDirCheckbox));
        if (!testDirCheckbox.findElement(By.xpath("./../div/input")).isSelected()) {
            testDirCheckbox.click();
        }
    }

    public boolean pressShowButton() {
        try {
            waitOne.until(ExpectedConditions.visibilityOf(showMoreCoursesButton));

            try {
                if (showMoreCoursesButton.isDisplayed()) {
                    showMoreCoursesButton.click();
                }
                return showMoreCoursesButton.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public int getCoursesCount() {
        return coursesList.size();
    }

    public void clickOnCourse(int i) {
        if (i < coursesList.size()) {
            coursesList.get(i).click();
        }
    }

    public String getTitle(int i) {
        if (i < coursesList.size()) {
            try {
                return coursesList.get(i).findElement(By.xpath(".//h6/div")).getText();
            } catch (NoSuchElementException e) {
                return null;
            }
        }
        return null;
    }
}
