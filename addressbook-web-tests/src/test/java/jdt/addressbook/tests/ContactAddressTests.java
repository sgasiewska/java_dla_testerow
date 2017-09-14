package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  private final Properties properties;

  public ContactAddressTests()  {
    properties = new Properties();

  }
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size()==0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname(properties.getProperty("web.firstname"))
              .withLastname(properties.getProperty("web.lastname"))
              .withAddress(properties.getProperty("web.addres")).withMail(properties.getProperty("web.mail"))
        //      .withGroup(properties.getProperty("web.group"))
              .withHomePhone(properties.getProperty("web.homePhone")).withMobiePhone(properties.getProperty("web.mobilePhone"))
              .withWorkPhone(properties.getProperty("web.workPhone"))
              .withMail(properties.getProperty("web.mail")).withMail2(properties.getProperty("web.mail2"))
              .withMail3(properties.getProperty("web.mail3")), true);
    }
  }
  @Test
  public void testContactAddress(){

    ContactData contact=app.db().contacts().iterator().next();
    app.goTo().contactPage();
    ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    verifyContactListInUI();
  }
}
