package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().contactPage();
    List<ContactData> before= app.contact().list();
    ContactData contact =new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1");
   // int before= app.getContactHelper().getContactCount();
    app.contact().create(contact, true);
   // app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
   // int after= app.getContactHelper().getContactCount();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);
   // Assert.assertEquals(after,before+1);
  }

}
