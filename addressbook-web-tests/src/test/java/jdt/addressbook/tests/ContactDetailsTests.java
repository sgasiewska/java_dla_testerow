package jdt.addressbook.tests;

import jdt.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase{

  @Test

  public void testContactPhones(){
    app.goTo().contactPage();
    ContactData contact=app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm =app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllInformations(), equalTo(mergeInformations(contactInfoFromEditForm)));
  }
  private String mergeInformations(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) ->!s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}

