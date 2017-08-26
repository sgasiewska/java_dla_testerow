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
    ContactData contact =new ContactData().withFirstname("name1").withAddress("address").withEmail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);
  }

}
