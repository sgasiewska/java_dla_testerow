package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
   @Test
  public void testContactDeletion(){

     app.getNavigationHelper().gotoContactPage();
     int before= app.getGroupHelper().getGroupCount();
     if (! app.getContactHelper().isThereAContact()){
       app.getContactHelper().createContact(new ContactData("name1", "ln1", "address", "123456789", "test@test.pl","test1"),true);
     }

     app.getContactHelper().selectContact();
     app.getContactHelper().deleteSelectedContact();
     app.getContactHelper().submitContactDeletion();
     int after= app.getGroupHelper().getGroupCount();
     Assert.assertEquals(after,before-1);


   }

}
