package jdt.addressbook.appmanager;

import jdt.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase{


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhome());
    type(By.name("email"),contactData.getEmail());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {

    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }
}
