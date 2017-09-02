package jdt.addressbook.appmanager;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

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
    contactCache=null;
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

    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[td[input[@value='" + id + "']]]/td[8]")).click();
  }
   /* WebElement checkbox= wd.findElement(By.cssSelector(String.format("input{[value='%s']",id)));
    WebElement row =checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells =row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
*/
  // wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[td[input[@value='"+ id +"']]]/td[8]")).click();
    //wd.findElements(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElements(By.xpath(String.format("/tr[.//input[@value='%s']]/td[8]/a",id))).click();
    //wd.findElements(By.cssSelector(String.format("a[href='edit.php?id=%s'}", id))).click();

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm (contact,true);
    submitContactCreation();
    contactCache=null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    submitContactDeletion();
    contactCache=null;
    returnToContactPage();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache=null;

  public Contacts all() {
    if(contactCache!=null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement>wiersze= wd.findElements(By.xpath("//table[@id='maintable']//tr[@name='entry']"));

    for (WebElement element:wiersze){
      List<WebElement> cells = element.findElements(By.tagName("td"));

      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      .withHomePhone(phones[0]).withMobiePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname= wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname= wd.findElement(By.name("lastname")).getAttribute("value");
    String home= wd.findElement(By.name("home")).getAttribute("value");
    String mobile= wd.findElement(By.name("mobile")).getAttribute("value");
    String work= wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobiePhone(mobile).withWorkPhone(work);

  }
}
