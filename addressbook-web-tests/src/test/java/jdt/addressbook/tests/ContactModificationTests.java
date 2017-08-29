package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData().withFirstname("name1").withLastname("ln1")
              .withAddress( "address").withPhome("123456789").withEmail("test@test.pl").withGroup( "test1"), true);
    }
  }

  @Test
  public void testContactModification() {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().initContactModification(before.size()-1);
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("name1").withLastname("ln1")
            .withAddress( "address").withPhome("123456789").withEmail("test@test.pl");
    app.contact().modifi(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() );
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);

  }


}
