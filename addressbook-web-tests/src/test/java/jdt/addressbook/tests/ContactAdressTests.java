package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdressTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstname("name1").withLastname("ln1")
              .withAddress( "address").withEmail("test@test.pl").withGroup( "test1")
              .withHomePhone("+1111").withMobiePhone("(11)999").withWorkPhone("22 22 22")
              .withMail("test@qq.pl").withMail2("test2@ww.pl").withMail3("test3@ew.pl") ,true);
    }
  }
  @Test
  public void testContactAdress(){
    app.goTo().contactPage();
    ContactData contact=app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

   assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }
}
