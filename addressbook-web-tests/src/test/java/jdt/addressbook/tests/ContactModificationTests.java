package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
    }

    List<GroupData> before= app.getGroupHelper().getGroupList();

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl", null),false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());


  }
}
