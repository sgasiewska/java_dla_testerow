package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl", "test1"), true);
    }
  }
    @Test
  public void testContactDeletion(){

     List<ContactData> before = app.getContactHelper().getContactList();
     app.getContactHelper().selectContact(before.size()-1);
     app.getContactHelper().deleteSelectedContact();
     app.getContactHelper().submitContactDeletion();
     List<ContactData> after = app.getContactHelper().getContactList();
     Assert.assertEquals(after.size(),before.size()-1);

     before.remove(before.size() -1);
     Assert.assertEquals(before, after);


   }

}
