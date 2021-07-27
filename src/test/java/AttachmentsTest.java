import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {

    @Test
    public void testAttachments() {
        open("http://github.com");
        step("Сделали screenshot с помощью Аннотации", () -> {
            annotatedAttachment();
        });

        step("Сделали screenshot с помощью метода", () -> {
            String source = WebDriverRunner.getAndCheckWebDriver().getPageSource();
            Allure.attachment("Исходный код страницы", source);
        });
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] annotatedAttachment() {
        return Selenide.screenshot(OutputType.BYTES);
    }

}