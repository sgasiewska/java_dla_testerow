package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();
    int before= app.getGroupHelper().getGroupCount();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
    }

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl", null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();

    int after= app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before);


  }
}
