package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import net.sourceforge.htmlunit.cyberneko.HTMLElements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    private Button Trade;

    @FXML
    private void ClickBtn(ActionEvent event)  {
        event.consume();
        Trade.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println("xcvbn");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("TTUJur");
            caps.setJavascriptEnabled(true);
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    "C:\\Users\\SedHakobyan\\Desktop\\phantomjs.exe");

            WebDriver mydrv = new PhantomJSDriver(caps);

            mydrv.get("https://www.myscore.com.ua/");
            mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            WebElement elo = mydrv.findElement(By.cssSelector("tr.tr-first stage-scheduled.td.cell_sa score")); //#g_1_SQSoR30L > td.cell_sa.score,  tr#g_1_SQSoR30L.tr-first.stage-scheduled
            mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            System.out.println("tag name ="+ elo.getText());
            elo.click();



            try {
                WebDriverWait wait = new WebDriverWait(mydrv, 10);

                Actions ob =new Actions(mydrv);
                ob.click(elo);
                Action action= ob.build();
                action.perform();
                mydrv.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                int i= 0;
          for (String st:mydrv.getWindowHandles()) {
              i++;
              mydrv.switchTo().window(st);
              System.out.println("link ="+mydrv.getCurrentUrl());
              FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg"+i));
              System.out.println(mydrv.getWindowHandles().size());
          }

            }
            catch (IOException e)

            {
                e.printStackTrace();
            }
            });

    }
}
