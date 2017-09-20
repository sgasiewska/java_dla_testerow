package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import jdt.addressbook.model.Contacts;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
  private final Properties properties;
  public AddContactToGroupTests()  {
    properties = new Properties();
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("grupa1"));
    }
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
  public void testAddContactToGroup(){
  Contacts contactsBefore = app.db().contacts();
  Groups groups = app.db().groups();
  GroupData groupsBefore = groups.iterator().next();
  ContactData contact = contactsBefore.iterator().next()
         .inGroup(groupsBefore);

  app.contact().selectContactById(contact.getId());
  app.contact().chooseGroup(contact);
  app.contact().addContacttoGroup();

  GroupData groupsAfter = app.db().groups().iterator().next();
  Contacts dbContacts = groupsAfter.getContacts();
  Contacts uiContacts = app.contact().all();
  assertThat(uiContacts, equalTo(dbContacts.stream()
          .map((g) ->  new ContactData().withId(g.getId()).withFirstname(g.getFirstname())
                  .withLastname(g.getLastname()).withAddress(g.getAddress()))
          .collect(Collectors.toSet())));

}
}
