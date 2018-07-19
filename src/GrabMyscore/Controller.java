package GrabMyscore;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import GrabMyscore.pojo.Gbs;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static org.apache.log4j.Logger.getLogger;

public class Controller {
    private static String css_team_name="#flashscore > div.team-primary-content > div.home-box.logo-enable > div.team-text.tname-home > div > div > a";
    private String team_name;
    private ArrayList <String> complect =new ArrayList<String>(); //each element is  team name|bookmaker|status;
    private ObservableList<Gbs> gbsData = FXCollections.observableArrayList();
    @FXML
    private Button Trade;
    @FXML
    private Button data;
    @FXML
    private Button Back;
    @FXML
    private ProgressBar progress=new ProgressBar();
    @FXML
    private ProgressIndicator progind =new ProgressIndicator(0);
    @FXML
    private  TableView <Gbs> table = new TableView<>(gbsData);
    @FXML
    private TableColumn <Gbs,String> Team;
    @FXML
    private TableColumn <Gbs,String> Bookmaker;
    @FXML
    private TableColumn <Gbs,String> Blocked;
   @FXML
   private AnchorPane mainpanel;
   private static final  Logger loger_= getLogger(Controller.class);


    @FXML
    private void ClickBtn() {


       // progress.setVisible(true);

        progind.setVisible(true);
        Trade.setVisible(false);
        Task<Void> task = new Task<Void>(){
            @Override

            public Void call() throws Exception {
                 // fill_tb();
                complect.clear();
                    //System.out.println("xcvbn");
                   // String pathphantom = Main.class.getResource("pic/phantomjs.exe").getFile();

                ClassLoader classLoader = getClass().getClassLoader();

               // File file = new File(classLoader.getResource("GrabMyscore/pic/phantomjs.exe").getFile());

              // File file = new File(this.getClass().getResource(""));
               // String configFile = this.getClass().getResource("pic/phantomjs.exe").getPath();

      //String phantomway_= file.getParent().substring(0,3)+"\\"+ "phantomjs.exe";
             //  FileUtils.writeStringToFile(new File("d:/phantom_log.txt"), file.getPath()); //write to log


                Path currentRelativePath = Paths.get("");
             String  configFile=currentRelativePath.toAbsolutePath().toString()+"\\"+"phantomjs.exe";
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setBrowserName("TTUJur");
                    caps.setJavascriptEnabled(true);
                    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, configFile); //"C:\\Users\\SedHakobyan\\Desktop\\phantomjs.exe");
                    loger_.info("path = "+ configFile);



                    WebDriver mydrv = new PhantomJSDriver(caps);
                    loger_.info("after phantom = " + mydrv.getTitle());
                    mydrv.get("https://www.myscore.com.ua/");
                    mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                    mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                    int games = mydrv.findElements(By.cssSelector("td.cell_sa.score")).size();
                  System.out.println("tag name =" + "size = " + mydrv.findElements(By.cssSelector("td.cell_sa.score")).size());
                int gsize = mydrv.findElements(By.cssSelector("td.cell_sa.score")).size();

                    for (int i=0;i<gsize;i++)
                    {
                        mydrv.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                       // FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg"));
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
                            FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.team_name.jpeg"));
                            team_name = mydrv.findElement(By.cssSelector(css_team_name)).getText();
                            //mydrv.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                           // FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" +"sed"));
                            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css_team_name)));
                            loger_.info("team name = "+ mydrv.findElement(By.cssSelector(css_team_name)).getText());
                            System.out.println("team name = "+ mydrv.findElement(By.cssSelector(css_team_name)).getText());
                            String css ="#a-match-odds-comparison";
                            if (isElementPresentBycss(css,mydrv)) {
                                WebElement elo1 = mydrv.findElement(By.cssSelector("#a-match-odds-comparison")); // "Коэффициенты" click element;
                                elo1.click();

                                try {

                                    Thread.sleep((1000));
                                } catch (InterruptedException e) {

                                }


                              //  FileUtils.copyFile(((TakesScreenshot) mydrv).getScreenshotAs(OutputType.FILE), new File("d:\\myscore.com.ua.jpeg" + 6));


                                for (WebElement el : mydrv.findElements(By.cssSelector("#odds_1x2 > tbody > tr"))) {

                                    // System.out.println("root element text = " + part.findElement(By.cssSelector("td.kx > span")).getAttribute("class").toString()); //System.out.println("root element text = "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.bookmaker > div > a")).getAttribute("title").toString()+ "|||| "+ mydrv.findElement(By.cssSelector("#odds_1x2 > tbody > tr:nth-child(2) > td.kx > span")).getAttribute("class").toString() );
                                    // System.out.println("root element text = " + get_bookStatus(el)+"||| = "+ mydrv.findElements(By.cssSelector("#odds_1x2 > tbody > tr")).size()+"||="+ el.getAttribute("class"));
                                    if (get_bookStatus(el).contains("blocked")) {
                                        complect.add(team_name + "|" + get_bookStatus(el));
                                        break;
                                    }
                                }

                            }


                           // System.out.println("tpel i="+i);
                            mydrv.switchTo().window(hw.get(1)).close();
                            mydrv.switchTo().window(hw.get(0));

                        } catch (Exception e)

                        {
                            e.printStackTrace();
                        }


                    }



                mydrv.close();

                Runtime.getRuntime().exec("taskkill /F /IM phantomjs.exe");

                progind.setVisible(false);

                //progress.setVisible(false);

                get_from_complect();
                //get_data();
                table.setVisible(true);
                Back.setVisible(true);

                Trade.setVisible(false);

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
        System.out.println("in func get_data");
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
    loger_.info("Elements is absent");
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

     @FXML
     private void Backclick ()
     {
         gbsData.clear();
         Back.setVisible(false);
         table.setVisible(false);
         Trade.setVisible(true);
     }


    private String get_bookStatus (WebElement element)
    {
        String bookmaker = element.findElement(By.cssSelector("td.bookmaker > div.blink > a")).getAttribute("title"); // add bookmaker name;;

        int size= element.findElements(By.cssSelector("td.kx > span")).size();
        WebElement le = element.findElement(By.cssSelector("td.kx > span"));

       if (le.getAttribute("class").toString().contains("not-published"))
           return bookmaker +"|"+ "blocked";

        return "none"; // element.findElement(By.cssSelector("td.kx > span")).getAttribute("class").toString();

    }

    private void get_from_complect()
    {
        for (int i=0;i<complect.size();i++)
            fill_tb(complect.get(i));

    }


    private void fill_tb(String team_row)

    {

        //String s ="inter|betfair|yes";
        String part1;
        String part2;
        String part3;
      StringTokenizer token= new  StringTokenizer (team_row,"|");
      if (token.countTokens()==3)
      {
          part1=token.nextToken();
          part2=token.nextToken();
          part3=token.nextToken();
      }

         else {
          throw new Error ("No enough token");
      }

        Gbs gbs =new Gbs(part1,part2,part3);
        gbsData.add(gbs);
        Team.setCellValueFactory(new PropertyValueFactory<Gbs, String>("tname"));
        Bookmaker.setCellValueFactory(new PropertyValueFactory<Gbs,String>("bookname"));
        Blocked.setCellValueFactory(new PropertyValueFactory<Gbs,String>("status"));
        table.setItems(gbsData);

    }


}



