package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  private final Properties properties;
  public ContactDeletionTests()  {
    properties = new Properties();

  }
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size()==0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname(properties.getProperty("web.firstname"))
              .withLastname(properties.getProperty("web.lastname"))
              .withAddress(properties.getProperty("web.addres")).withMail(properties.getProperty("web.mail"))
            //  .withGroup(properties.getProperty("web.group"))
              .withHomePhone(properties.getProperty("web.homePhone")).withMobiePhone(properties.getProperty("web.mobilePhone"))
              .withWorkPhone(properties.getProperty("web.workPhone"))
              .withMail(properties.getProperty("web.mail")).withMail2(properties.getProperty("web.mail2"))
              .withMail3(properties.getProperty("web.mail3")), true);
    }
  }
    @Test
  public void testContactDeletion(){

     Contacts before = app.db().contacts();
     ContactData deletedContact = before.iterator().next();
     app.contact().delete(deletedContact);
      assertThat(app.contact().count(),equalTo(before.size()-1));
     Contacts after = app.db().contacts();
     assertThat(after,equalTo(before.without(deletedContact)));
      verifyContactListInUI();
   }

}
