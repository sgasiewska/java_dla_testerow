package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  private final Properties properties;
  public ContactModificationTests()  {
    properties = new Properties();

  }
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size()==0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname(properties.getProperty("web.firstname"))
              .withLastname(properties.getProperty("web.lastname"))
              .withAddress(properties.getProperty("web.addres")).withMail(properties.getProperty("web.mail"))
              .withGroup(properties.getProperty("web.group"))
              .withHomePhone(properties.getProperty("web.homePhone")).withMobiePhone(properties.getProperty("web.mobilePhone"))
              .withWorkPhone(properties.getProperty("web.workPhone"))
              .withMail(properties.getProperty("web.mail")).withMail2(properties.getProperty("web.mail2"))
              .withMail3(properties.getProperty("web.mail3")), true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("name1").withLastname("ln1")
            .withAddress( "address").withPhome("123456789").withMail("test@test.pl");
    app.contact().initContactModificationById(modifiedContact.getId());
    app.contact().modifi(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }
}
