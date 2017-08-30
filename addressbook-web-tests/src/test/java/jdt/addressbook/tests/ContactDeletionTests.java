package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

     Contacts before = app.contact().all();
     ContactData deletedContact = before.iterator().next();
     app.contact().delete(deletedContact);
     Contacts after = app.contact().all();
     assertThat(after.size(),equalTo(before.size()-1));
     assertThat(after,equalTo(before.without(deletedContact)));

   }

}
