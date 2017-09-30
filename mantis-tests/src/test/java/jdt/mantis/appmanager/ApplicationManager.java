package jdt.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;


  public ApplicationManager(String browser) {

    this.browser = browser;
    properties = new Properties();

  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

  }




  public void stop() {
    if(wd!=null){
      wd.quit();
    }
  }


  public String getProperty(String key) {
    return properties.getProperty(key);

  }

  public FtpHelper ftp(){
    if(ftp==null){
      ftp =new FtpHelper(this);
    }
    return ftp;
  }
  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public RegistrationHelper registration() {
    // jako parametr będzie przekazywany link na Application manager
    if (registrationHelper ==null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }
//Leniwa inicjalizacja, met inincjalizoujaca sterownik w chwili pierwszego zwrócienia
  //sterownik przeglądarki inicjalizuje się gdy ktoś się do niego zwróci
  public WebDriver getDriver() {
    // jeżeli nie jest zainicjowany wd to trzeba to zrobic
    if(wd==null){
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();

      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));

    }
    return wd;
  }
  public MailHelper mail(){
    if (mailHelper==null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }
}