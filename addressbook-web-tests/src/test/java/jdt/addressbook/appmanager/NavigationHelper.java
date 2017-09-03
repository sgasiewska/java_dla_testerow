package jdt.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);

  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;

    }
    click(By.linkText("groups"));
  }

  public void contactPage() {
    if(isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.id("content"));
  }


}
