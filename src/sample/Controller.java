package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    private Button Trade;
    @FXML
    private ProgressBar progress=new ProgressBar();
    @FXML

        private void ClickBtn(ActionEvent event) {
        event.consume();
        progress.progressProperty().bind(task.progressProperty());
        task.run();



    }
    private Task task = new Task() {
        @Override
        protected Object call() throws Exception {

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
                //WebElement elo = mydrv.findElement(By.cssSelector("td.cell_sa.score")); //#g_1_zmlHDRz3 > td.cell_sa.score
                WebElement elo = mydrv.findElements(By.cssSelector("td.cell_sa.score")).get(1);
                mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                System.out.println("tag name =" + "size = " + mydrv.findElements(By.cssSelector("td.cell_sa.score")).size());
                elo.click();


                try {
                    WebDriverWait wait = new WebDriverWait(mydrv, 30);

                    Actions ob = new Actions(mydrv);
                    ob.click(elo);
                    Action action = ob.build();
                    action.perform();
                    mydrv.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
                    int i = 0;
                    System.out.println("home name = " + elo.getTagName());
                    List<String> hw = new ArrayList<String>();
                    for (String st : mydrv.getWindowHandles()) {
                        hw.add(st);
                        i++;
                        mydrv.switchTo().window(st);
                        System.out.println("link =" + mydrv.getCurrentUrl());
                        FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" + i));
                        System.out.println(mydrv.getWindowHandles().size());

                    }

                    mydrv.switchTo().window(hw.get(1));
                    WebElement elo1 = mydrv.findElement(By.cssSelector("#a-match-odds-comparison"));
                    elo1.click();
                    try {
                        Thread.sleep((1000));
                    } catch (InterruptedException e) {

                    }
                    FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" + 6));

                    WebElement part = mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr"));
                    System.out.println("root element text = " + part.findElement(By.cssSelector("td.bookmaker > div > a")).getAttribute("title").toString()); //System.out.println("root element text = "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.bookmaker > div > a")).getAttribute("title").toString()+ "|||| "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.kx > span")).getAttribute("class").toString() );
                 progress.progressProperty().unbind();

                } catch (IOException e)

                {
                    e.printStackTrace();
                }
            });

            return true;
        }
    };


    }

