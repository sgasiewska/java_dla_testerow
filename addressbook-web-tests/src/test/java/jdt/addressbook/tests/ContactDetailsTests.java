package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {
  private final Properties properties;
  public ContactDetailsTests()  {
    properties = new Properties();

  }
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname(properties.getProperty("web.firstname"))
              .withLastname(properties.getProperty("web.lastname"))
              .withAddress(properties.getProperty("web.addres")).withEmail(properties.getProperty("web.mail"))
              .withGroup(properties.getProperty("web.group"))
              .withHomePhone(properties.getProperty("web.homePhone")).withMobiePhone(properties.getProperty("web.mobilePhone"))
              .withWorkPhone(properties.getProperty("web.workPhone"))
              .withMail(properties.getProperty("web.mail")).withMail2(properties.getProperty("web.mail2"))
              .withMail3(properties.getProperty("web.mail3")), true);
    }
  }

  @Test

  public void testContactPhones() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    assertThat(mergeInformations1(contactInfoFromDetailsForm), equalTo(mergeInformations2(contactInfoFromEditForm)));
  }

  private String mergeInformations1(ContactData contact) {
    return Arrays.asList(contact.getAllInformations())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining(""));
  }

  private String mergeInformations2(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
            contact.getMail(), contact.getMail2(), contact.getMail3())
            .stream().filter((c) -> !c.equals(""))
            .map(ContactDetailsTests::cleaned).collect(Collectors.joining(""));

  }

  public static String cleaned(String data) {
    return data.replaceAll("\\s", "").replaceAll("[W:]", "")
            .replaceAll("[H:]", "").replaceAll("[M:]", "")
            .replaceAll("emberoftest1", "");
  }

}

