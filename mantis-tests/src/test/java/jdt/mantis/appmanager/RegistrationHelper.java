package jdt.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {

  private final ApplicationManager app;
  //bedzie nam potrzebna przegladarka
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app=app;
    // link do sterownika, po tym można kożystać ze sterownika  przegladarki,
    wd=app.getDriver();
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");

  }
}
