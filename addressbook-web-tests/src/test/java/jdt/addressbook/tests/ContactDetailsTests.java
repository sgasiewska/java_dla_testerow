package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("name1").withLastname("ln1")
              .withAddress("address").withEmail("test@test.pl").withGroup("test1")
              .withHomePhone("+1111").withMobiePhone("(11)999").withWorkPhone("22 22 22")
              .withMail("test@qq.pl").withMail2("test2@ww.pl").withMail3("test3@ew.pl"), true);
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

