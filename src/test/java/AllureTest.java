import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllureTest {
    @Step

    public void checkSumStep(int number1, int number2, int expectedSum) {
        Assertions.assertEquals(expectedSum, (number1 + number2));
    }

    @Test
    @Description(value = "Проверка суммы цифор")
    public void checkSumStepTest() {
        checkSumStep(1, 2, 3);
        checkSumStep(2, 5, 7);
    }

    @Step("Проверка разности {number1} и числа {number2}")
    public void checkSubtractionStep(int number1, int number2, int expectedSum) {
        Assertions.assertEquals(expectedSum, (number1 - number2));
    }

    @Test
    public void checkSubtractionStepTest() {
        checkSubtractionStep(7, 1, 6);
        checkSubtractionStep(9, 9, 0);
    }

    @Attachment
    public static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("resources", resourceName));
    }


    @Attachment
    public byte[] takeScreenShot() throws AWTException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(
                new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
                "png", baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    @Step("Проверка эквивалентности строк {srt1} и {srt2}")
    public void checkEqualsString(String srt1, String str2) throws AWTException, IOException {
        Assertions.assertEquals(srt1, str2);
        getBytes("1.png");
        getBytes("2.txt");
        takeScreenShot();
    }

    @Test
    public void checkEqualsStringTest() throws AWTException, IOException {
        String str = "Учись";
        checkEqualsString(str, str);
    }

    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    @Test
    public void sumTest() {
        checkSumStep(2, 3, 5);
    }

    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Вычитание")
    @Test
    public void SubtractionTest() {
        checkSubtractionStep(9, 2, 7);
    }

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Features(value = {@Feature(value = "Простые математические операции"), @Feature(value = "Тригонометрия")})
    @Stories(value = {@Story(value = "Синус"), @Story(value = "Синусоида")})
    @Test
    public void sinTest() {
        assertEquals((Math.sin(0)), 0.0);
    }

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Features(value = {@Feature(value = "Простые математические операции"), @Feature(value = "Тригонометрия")})
    @Stories(value = {@Story(value = "Косинус"), @Story(value = "Косинусоида")})
    @Test
    public void cosTest() {
        assertEquals((Math.cos(0)), 1.0);

    }

    public static void addLinkGoogle() {
        String link = "https://www.google.com/";
        Allure.addAttachment("Result", "text/html", link);
    }

    @Description(value = "Добавить ссылку сайта Google")
    @Test
    public void addLinkGoogleTest() {
        addLinkGoogle();
    }

    @Link(url = "https://www.google.com")
    @Test
    public void addActiveLink() {

    }


    @Step(value = "Проверка равно ли рандомное число {randomNumber} числу 0")
    @Flaky
    @Test
    public void flakyTest() {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 3);
        if (randomNumber == 0) {
            Assertions.assertTrue(true);
        } else Assertions.assertTrue(false);
    }

}
