package jdt.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import jdt.addressbook.model.ContactData;
import jdt.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
  try( BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))){
    String xml ="";
    String line =reader.readLine();
    while (line !=null){
      xml+=line;
      line =reader.readLine();
    }
    XStream xstream= new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g)-> new Object [] {g}).collect(Collectors.toList()).iterator();
  }

  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))){
      String json ="";
      String line =reader.readLine();
      while (line !=null){
        json+=line;
        line =reader.readLine();
      }
      Gson gson=new Gson ();
      List<ContactData> contacts=gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g)-> new Object [] {g}).collect(Collectors.toList()).iterator();
    }
  }
  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {

    app.goTo().contactPage();
    Contacts before= app.db().contacts();
    File photo=new File("src/test/resources/kot.png");
    app.contact().create(contact, true);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo=new File("src/test/resources/kot.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

    }

  @Test (enabled = false)
  public void testBadContactCreation() {

    app.goTo().contactPage();
    Contacts before= app.contact().all();
    ContactData contact =new ContactData().withFirstname("name1'").withLastname("test").withAddress("address").withMail("test@test.pl").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}
