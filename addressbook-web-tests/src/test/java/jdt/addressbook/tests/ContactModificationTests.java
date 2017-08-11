package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();



  }
}
