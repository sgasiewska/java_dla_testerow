package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoContactPage();
    int before= app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
    int after= app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before+1);
  }

}
