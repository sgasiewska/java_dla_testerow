package jdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(FirefoxDriver wd) {

    super(wd);
  }
  public void login(String username, String password) {

  //  wd.findElement(By.id("content")).click();
  //  wd.findElement(By.id("LoginForm")).click();
   // wd.findElement(By.name("user")).click();
   // wd.findElement(By.id("LoginForm")).click();
  //  wd.findElement(By.id("content")).click();
    type(By.name("user"),username);
    type(By.name("pass"),password);

    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
