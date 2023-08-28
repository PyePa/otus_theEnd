package Runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractOtusPage;
import pages.CoursePage;
import pages.CoursesPage;
import pages.EventsPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    public static final String OTUS_COURSES_URL = "https://otus.ru/catalog/courses?categories=testing";
    public static final String OTUS_EVENTS_URL = "https://otus.ru/events/near/";

    WebDriver driver;
    final Logger logger = LogManager.getLogger(MainTest.class);
    private boolean cookie = false;
    private boolean chat = false;

    @BeforeAll
    public static void install() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    private void removeCookieAndChat(AbstractOtusPage po) {
        if (!cookie) {
            cookie = po.pressCookieButton();
        }
        if (!chat) {
            chat = po.pressChatButton();
        }
        waiting();
    }

    private CoursesPage loadCoursesPage() {
        CoursesPage coursesPage = new CoursesPage(driver);
        waiting();
        removeCookieAndChat(coursesPage);
        waiting();
        coursesPage.clickTestDirCb();

        while (coursesPage.pressShowButton()) {
            logger.info("Показать кнопку 'еще' ");
        }
        return coursesPage;
    }

    private void waiting() {
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void CoursesInfo() {
        driver.get(OTUS_COURSES_URL);
        CoursesPage coursesPage = loadCoursesPage();
        assertEquals(12, coursesPage.getCoursesCount(), "Количество курсов");


        for (int i = 0; i < coursesPage.getCoursesCount(); i++) {
            String title = coursesPage.getTitle(i);
            logger.info(String.format("Проверка курса %d: %s", i + 1, title));
            coursesPage.clickOnCourse(i);

            CoursePage coursePage = new CoursePage(driver);
            waiting();
            assertEquals(title, coursePage.getTitle(), "Название курса");
            assertTrue(coursePage.hasDuration(), "Продолжительность обучение");
            assertTrue(coursePage.hasFormat(), "Формат обучения");
            assertTrue(coursePage.hasDescription(), "Описание курса");
            driver.navigate().back();
            waiting();
            coursesPage = loadCoursesPage();
            waiting();
        }

    }

    @Test
    void Events() {
        driver.get(OTUS_EVENTS_URL);
        EventsPage eventsPage = new EventsPage(driver);
        waiting();
        removeCookieAndChat(eventsPage);
        waiting();
        eventsPage.scrollDown();
        logger.info("Количество событий {}", eventsPage.eventsCount());
        assertTrue(eventsPage.checkDates(), "Неподходящая дата!");
    }

    @Test
    void OpenLessons() {
        driver.get(OTUS_EVENTS_URL);
        EventsPage eventsPage = new EventsPage(driver);
        waiting();
        removeCookieAndChat(eventsPage);
        waiting();
        eventsPage.filterOpenWebinars();
        eventsPage.scrollDown();
        logger.info("Количество событий {}", eventsPage.eventsCount());
        assertTrue(eventsPage.checkOpenWebinars("Открытый вебинар"), "Неверный тип!");
    }
}
