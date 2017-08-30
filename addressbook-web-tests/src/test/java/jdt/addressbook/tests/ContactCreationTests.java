package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;

import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().contactPage();
    Set<ContactData> before= app.contact().all();
    ContactData contact =new ContactData().withFirstname("name1").withLastname("test").withAddress("address").withEmail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());

    before.add(contact);

     Assert.assertEquals(before, after);
  }

}
