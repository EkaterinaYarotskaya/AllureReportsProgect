import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class AllureTest {
    @Step
    public int sunn(int a, int b) {
        return a + b;
    }

    @Test
    public void additionOfNumbers() {

        assertThat(sunn(1, 3), is(4));
    }
    @Test
    public void additionOfNumbersFail() {

        assertThat(sunn(1, 3), is(4));
    }
}
