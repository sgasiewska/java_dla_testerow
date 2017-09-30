package jdt.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

//  private final ApplicationManager app;
  //bedzie nam potrzebna przegladarka
//  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    super(app);
//    this.app=app;
    // link do sterownika, po tym można kożystać ze sterownika  przegladarki,
   // wd=app.getDriver();
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
   // wd.findElement(By.name("username")).sendKeys(username);
    wd = app.getDriver();
    type(By.name("username"),username);
    type(By.name("email"),email);
    click(By.cssSelector("input[value='Zapisz się']"));
  }

  public void finish(String confirmationLink, String password) {
    //przechodzimy po linku
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//button[@type='submit']"));
  }
}
