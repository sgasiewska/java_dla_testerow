package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("name1").withLastname( "ln1").withAddress( "address").withPhome( "123456789")
              .withEmail( "test@test.pl").withGroup("test1"),true);
    }
  }
    @Test
  public void testContactDeletion(){

     List<ContactData> before = app.contact().list();
     int index=before.size()-1;
     app.contact().delete(index);
     List<ContactData> after = app.contact().list();

     Assert.assertEquals(after.size(),before.size()-1);

     before.remove(before.size()-1);
     Assert.assertEquals(before, after);


   }



}
