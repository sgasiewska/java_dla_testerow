package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Comparator;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {

    app.goTo().contactPage();
    List<ContactData> before= app.contact().list();
    ContactData contact =new ContactData().withFirstname("name1").withLastname("test").withAddress("address").withEmail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
     after.sort(byId);
     Assert.assertEquals(before, after);
  }

}
