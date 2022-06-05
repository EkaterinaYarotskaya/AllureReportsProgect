import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(Watcher.class)
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

    @Description(value = "Проверка эквивалентности знычений ")
    @Step
    public void numerationEquivalence(int a, int b) {
        Assertions.assertEquals(a, b);
    }

    @Test
    @Description(value = "Проверка эквивалентности цифр")
    public void SimpleTest() {
        numerationEquivalence(1, 1);
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

    @Step("String Equivalence Check {srt1} and {str2} ")
    public void checkEqualsString(String srt1, String str2) throws IOException {
        Assertions.assertEquals(srt1, str2);
        getBytes("1.png");
        getBytes("2.txt");
    }

    @Epic(value = "Строки")
    @Feature(value = "Проверка совпадения слов")
    @Test
    public void checkEqualsStringTest() throws IOException {
        String string1 = "Учись";
        String string2 = "Учись";
        checkEqualsString(string1, string2);
    }

    @Step("Вывод слов в {string1} и {string2}  в одно преложение")
    public void connection(String string1, String string2) {
        String string = string1 + string2;
        System.out.println(string);
    }

    @Epic(value = "Строки")
    @Features(value = {@Feature(value = "Объединение слов в предложение"), @Feature(value = "Проверка совпадения слов")})
    @Test
    public void connectionTest() throws IOException {
        String s = "Учись, ";
        String s2 = "студент";
        connection(s, s2);
        checkEqualsString(s, s);
    }

    @Test
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")

    public void severityGroupTest() {
        checkSubtractionStep(7, 1, 6);
    }

    @Test
    @Severity(value = SeverityLevel.TRIVIAL)
    public void severityTest() throws IOException {
        checkEqualsString("TRIVIAL", "TRIVIAL");
    }

    @Owner("Nike")
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    @Test
    public void sumTest() {
        checkSumStep(2, 3, 5);
    }

    @Owner("Nike")
    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Вычитание")
    @Test
    public void SubtractionTest() {
        checkSubtractionStep(9, 2, 7);
    }

    @Owner("Nike")
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

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Feature(value = "Тригонометрия")
    @Story(value = "Арксинус")
    @Test
    public void findArcsin() {
        assertEquals((Math.asin(0)), 0.0);
        System.out.println(Math.asin(0));
    }

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Feature(value = "Тригонометрия")
    @Story(value = "Арккосинус")
    @Test
    public void findArccos() {
        assertEquals((Math.acos(1)), 0.0);
        System.out.println(Math.acos(0));
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

    @Step(value = "Проверка равно ли рандомное число  числу 0")
    @Test
    @Flaky
    public void flakyTest() {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 3);
        if (randomNumber == 0) {
            Assertions.assertTrue(true);
        } else Assertions.assertTrue(false);
    }

    @Owner(value = "Екатерина")
    @Test
    public void ownerTest() {
        checkSumStep(1, 1, 2);
    }

    @Description(value = "Вызываем  IllegalArgumentException и делаем скриншот")
    @Test
    public void exсeptionTest() throws IOException, AWTException {
        throw new IllegalArgumentException();
    }

    @Story("Удаление последнего элемента")
    private static String removeLastChar(String s) {
        return (s == null || s.length() == 0) ? null : (s.substring(0, s.length() - 1));
    }


    @Attachment(fileExtension = ".txt")
    public String readText(String fileAddress) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress),
                "UTF-8"))) {
            Scanner scanner = new Scanner(br);
            String readString = new String();
            while (scanner.hasNext()) {
                String str = "\n";
                readString += scanner.nextLine() + str;
            }
            String result = removeLastChar(readString);
            return result;
        }
    }
    @Severity(SeverityLevel.CRITICAL)
    @Epic(value = "Строки")
    @Feature("Работа с текстовым файлом")
    @Story("Проверка совпадения слов")
    @Test
    public void SimpleTest2() throws IOException {
        String fileAddress = "resources/read.txt";
        String textFromFile = readText(fileAddress);

        checkEqualsString(textFromFile, "Привет!\nПрочти текст");
    }

    @Attachment(fileExtension = ".txt")
    public String writeText(String inputText, String fileAddress) throws IOException {
        try (FileWriter fw = new FileWriter(fileAddress)) {
            fw.write(inputText);
        }
        return readText(fileAddress);
    }
    @Severity(SeverityLevel.MINOR)
    @Epic(value = "Строки")
    @Feature("Работа с текстовым файлом")
    @Story("Внесение текста в документ")
    @Test
    public void WriteTest() throws IOException {
        String fileAddress = "resources/write.txt";
        String inputText = "Добрый день!\nКак у вас дела?";
        String writtenText = writeText(inputText, fileAddress);
        Assertions.assertEquals(writtenText, inputText);
    }

    @Attachment
    public boolean clearFile(String fileAddress) throws IOException {
        try (FileWriter bufferedWriter = new FileWriter(fileAddress)) {
            bufferedWriter.write("");
        }
        return true;
    }

    @Epic(value = "Строки")
    @Feature("Работа с текстовым файлом")
    @Story("Очистка файла")
    @Test
    public void CleaningFileTest() throws IOException {
        String fileAddress = "resources/write.txt";
        String inputText = "Добрый день!\nКак у вас дела?";
        writeText(inputText, fileAddress);
        clearFile(fileAddress);
        String readText = readText(fileAddress);
        Assertions.assertEquals(null, readText);
    }

    @Test
    public void WriteTextTwice() {


    }
}
