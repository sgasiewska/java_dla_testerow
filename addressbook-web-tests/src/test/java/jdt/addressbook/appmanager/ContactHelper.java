package jdt.addressbook.appmanager;

import jdt.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }
  public void modifi(ContactData contact) {
    fillContactForm(contact, false);
    submitContactModification();
    returnToContactPage();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhome());
    type(By.name("email"), contactData.getEmail());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm (contact,true);
    submitContactCreation();
    returnToContactPage();
  }
  public void delete(int index) {
   selectContact(index);
   deleteSelectedContact();
   submitContactDeletion();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List <ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> wiersze =wd.findElements(By.tagName("tr"));

    for (WebElement element:wiersze){
      String firstname= element.getText();
     //int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
     int id = Integer.parseInt(element.findElement(By.xpath("//table[@id='maintable']//td/input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname));
    }
    return contacts;
  }
}
