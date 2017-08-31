package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().contactPage();
    Contacts before= app.contact().all();
    ContactData contact =new ContactData().withFirstname("name1").withLastname("test").withAddress("address").withEmail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
  @Test
  public void testBadContactCreation() {

    app.goTo().contactPage();
    Contacts before= app.contact().all();
    ContactData contact =new ContactData().withFirstname("name1'").withLastname("test").withAddress("address").withEmail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
