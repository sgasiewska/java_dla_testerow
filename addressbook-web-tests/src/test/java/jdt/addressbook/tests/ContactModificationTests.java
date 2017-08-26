package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
    }

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification();
 //   app.getContactHelper().fillContactForm(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl", null),false);
    ContactData contact = new ContactData("name1", "ln1", "address", "123456789", "test@test.pl", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

   before.remove(before.size() - 1);
   before.add(contact);

   Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
   before.sort(byId);
   after.sort(byId);

   Assert.assertEquals(before,after);


  }
}
