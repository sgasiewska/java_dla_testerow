package jdt.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    // wd.findElement(By.name("username")).sendKeys(username);
    wd = app.getDriver();
    type(By.name("username"),username);
    click(By.cssSelector("input[value='Zaloguj się']"));
    type(By.name("password"),password);
    click(By.cssSelector("input[value='Zaloguj się']"));
  }
  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//button[@type='submit']"));
  }

  public void goToUsersManagement() {
    click(By.linkText("Zarządzanie"));

    click(By.linkText("Zarządzanie użytkownikami"));
  }

  public void chooseUser(String user) {
    click(By.linkText(user));
  }

  public void initChangePass() {

    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }
}
