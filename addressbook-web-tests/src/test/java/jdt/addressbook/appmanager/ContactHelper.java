package jdt.addressbook.appmanager;

import jdt.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
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

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[title = Edit]")).get(index).click();
  }
  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[td[input[@value='"+ id +"']]]/td[8]")).click();
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
  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    submitContactDeletion();
    returnToContactPage();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Set<ContactData> all() {
    Set <ContactData> contacts = new HashSet<ContactData>();
    List<WebElement>wiersze= wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));

    for (WebElement element:wiersze){
      List<WebElement> cells = element.findElements(By.tagName("td"));

      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}
