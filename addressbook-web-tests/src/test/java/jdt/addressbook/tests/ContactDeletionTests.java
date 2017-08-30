package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData()
              .withFirstname("name1").withLastname( "ln1").withAddress( "address").withPhome( "123456789")
              .withEmail( "test@test.pl").withGroup("test1"),true);
    }
  }
    @Test
  public void testContactDeletion(){

     Set<ContactData> before = app.contact().all();
     ContactData deletedContact = before.iterator().next();
     app.contact().delete(deletedContact);
     Set<ContactData> after = app.contact().all();

     Assert.assertEquals(after.size(),before.size()-1);

     before.remove(deletedContact);
     Assert.assertEquals(before, after);


   }



}
