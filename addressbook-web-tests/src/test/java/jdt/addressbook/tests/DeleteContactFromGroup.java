package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContactFromGroup extends TestBase {
  private final Properties properties;

  public DeleteContactFromGroup() {
    properties = new Properties();
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grupa1"));
    }
      if (app.db().contacts().size() == 0) {
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
    public void testDeleteContactFromGroup () {
      Groups groups = app.db().groups();
      Contacts contactsBefore = app.db().contacts();
      GroupData groupsBefore = groups.iterator().next();
      Contacts dbContacts = groupsBefore.getContacts();
      if (dbContacts.size() == 0) {
        ContactData contact = contactsBefore.iterator().next().inGroup(groupsBefore);
        app.goTo().contactPage();
        app.contact().selectContactById(contact.getId());
        app.contact().chooseGroup(contact);
        app.contact().addContacttoGroup();
        dbContacts = app.db().groups().iterator().next().getContacts();
      }

      ContactData contact = dbContacts.iterator().next();
      app.goTo().contactPage();
      app.contact().selectGroup(groups);
      app.contact().selectContactById(contact.getId());
      app.contact().deleteContactFromGroup();
      //Assert.assertFalse(app.contact().isElementPresent(By.id(String.valueOf(contact.getId()))));

      GroupData groupsAfter = app.db().groups().iterator().next();
      Contacts dbContactsAfter = groupsAfter.getContacts();
      Contacts uiContacts = app.contact().all();

      assertThat(uiContacts, Matchers.equalTo(dbContactsAfter.stream()
              .map((g) -> new ContactData().withId(g.getId()).withFirstname(g.getFirstname())
                      .withLastname(g.getLastname()).withAddress(g.getAddress()))
              .collect(Collectors.toSet())));

    }
  }
