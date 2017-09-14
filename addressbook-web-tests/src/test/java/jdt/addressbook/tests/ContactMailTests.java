package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {
  private final Properties properties;

  public ContactMailTests()  {
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
  public void testContactMail(){
    app.goTo().contactPage();
    ContactData contact=app.contact().all().iterator().next();
    app.goTo().contactPage();
    ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));
    verifyContactListInUI();
  }

  private String mergeMails(ContactData contact) {
    return Arrays.asList(contact.getMail(),contact.getMail2(),contact.getMail3())
            .stream().filter((s) ->!s.equals(""))
            .map(ContactMailTests::cleaned)
            .collect(Collectors.joining("\n"));

  }
  public static String cleaned(String mail){
    return  mail.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
