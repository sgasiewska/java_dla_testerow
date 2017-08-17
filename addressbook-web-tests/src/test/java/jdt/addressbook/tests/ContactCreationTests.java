package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }

}
