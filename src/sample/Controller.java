package sample;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.javascript.host.Element;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import net.sourceforge.htmlunit.cyberneko.HTMLElements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Controller {
    private static String css_team_name="#flashscore > div.team-primary-content > div.home-box.logo-enable > div.team-text.tname-home > div > div > a";
    private String team_name;
    private ArrayList <String> complect =new ArrayList<String>(); //each element is  team name|bookmaker|status;
    @FXML
    private Button Trade;
    @FXML
    private Button data;
    @FXML
    private ProgressBar progress=new ProgressBar();
    @FXML
    private ProgressIndicator progind =new ProgressIndicator(0);
    @FXML

        private void ClickBtn() {

       // progress.setVisible(true);
        progind.setVisible(true);
        Task<Void> task = new Task<Void>(){
            @Override

            public Void call() throws Exception {

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
                    //WebElement elo = mydrv.findElements(By.cssSelector("td.cell_sa.score")).get(2);
                    mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                    int games = mydrv.findElements(By.cssSelector("td.cell_sa.score")).size();
                    System.out.println("tag name =" + "size = " + mydrv.findElements(By.cssSelector("td.cell_sa.score")).size());

                    for (int i=0;i<mydrv.findElements(By.cssSelector("td.cell_sa.score")).size();i++)
                    {
                        mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                        FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg"));
                        WebElement elo = mydrv.findElements(By.cssSelector("td.cell_sa.score")).get(i);

                        elo.click();
                        mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                        try {
                            WebDriverWait wait = new WebDriverWait(mydrv, 30);

                            Actions ob = new Actions(mydrv);
                            ob.click(elo);
                            Action action = ob.build();
                            action.perform();
                            mydrv.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

                            int j = 0;
                            System.out.println("home name = " + elo.getTagName());
                            List<String> hw = new ArrayList<String>();
                            for (String st : mydrv.getWindowHandles()) {
                                hw.add(st);
                               // j++;
                               // mydrv.switchTo().window(st);
                               // System.out.println("link =" + mydrv.getCurrentUrl());
                                //FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" + j));
                                //System.out.println(mydrv.getWindowHandles().size());

                           }

                            mydrv.switchTo().window(hw.get(1));
                            mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                            //complect.add(mydrv.findElement(By.cssSelector(css_team_name)).getText()+"|");
                            //team_name = mydrv.findElement(By.cssSelector(team_name)).getText();
                            //mydrv.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                            FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" +"sed"));
                            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css_team_name)));
                            System.out.println("team name = "+ mydrv.findElement(By.cssSelector(css_team_name)).getText());
                            String css ="#a-match-odds-comparison";
                            if (isElementPresentBycss(css,mydrv)) {
                                WebElement elo1 = mydrv.findElement(By.cssSelector("#a-match-odds-comparison")); // "Коэффициенты" click element;
                                elo1.click();

                                try {

                                    Thread.sleep((1000));
                                } catch (InterruptedException e) {

                                }


                                FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" + 6));

                                //WebElement part = mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr"));
                                for (WebElement el : mydrv.findElements(By.cssSelector("#odds_1x2 > tbody > tr"))) {

                                    // System.out.println("root element text = " + part.findElement(By.cssSelector("td.kx > span")).getAttribute("class").toString()); //System.out.println("root element text = "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.bookmaker > div > a")).getAttribute("title").toString()+ "|||| "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.kx > span")).getAttribute("class").toString() );
                                    // System.out.println("root element text = " + get_bookStatus(el)+"||| = "+ mydrv.findElements(By.cssSelector("#odds_1x2 > tbody > tr")).size()+"||="+ el.getAttribute("class"));
                                    if (get_bookStatus(el).contains("blocked")) {
                                        complect.add(team_name + "|" + get_bookStatus(el));
                                        break;
                                    }
                                }

                            }


                            System.out.println("tpel i="+i);
                            mydrv.switchTo().window(hw.get(1)).close();
                            mydrv.switchTo().window(hw.get(0));

                        } catch (IOException e)

                        {
                            e.printStackTrace();
                        }


                    }



                mydrv.close();
                Runtime.getRuntime().exec("taskkill /F /IM phantomjs.exe");

                progind.setVisible(false);
                //progress.setVisible(false);
                get_data();
                return null;
            }
        };
       // progress.progressProperty().bind(task.progressProperty());
       progind.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
       // progress.progressProperty().unbind();
       progind.progressProperty().unbind();
    }
    @FXML
    private void get_data ()
    {
        for (int i=0;i<complect.size();i++)
        {
            System.out.println("game ="+complect.get(i));
        }

    }



     public boolean isElementPresentBycss (String css, WebDriver drv) {

        boolean flag =true;

        try {
          //  drv.findElement(By.cssSelector(css));

            WebDriverWait wait = new WebDriverWait(drv, 20);  // 10 secs max wait
            wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector(css)));


        }
catch (ElementNotFoundException e) {
    System.out.println("Element chka");
            flag =false;

}
catch (Exception  e)
{
    System.out.println(" V error");
    flag =false;
}
return flag;
     }



    private String get_bookStatus (WebElement element)
    {
        String bookmaker = element.findElement(By.cssSelector("td.bookmaker > div.blink > a")).getAttribute("title"); // add bookmaker name;;

        int size= element.findElements(By.cssSelector("td.kx > span")).size();
        WebElement le = element.findElement(By.cssSelector("td.kx > span"));

       if (le.getAttribute("class").toString().compareToIgnoreCase("not-published odds-wrap  down")==0)
           return bookmaker+"|"+ "blocked";

        return "none"; // element.findElement(By.cssSelector("td.kx > span")).getAttribute("class").toString();

    }

}



