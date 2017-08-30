package jdt.addressbook.tests;

import jdt.addressbook.model.GroupData;
import jdt.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupModification (){

    Groups before= app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader( "test2").withFooter( "test3");
    app.group().modifi(group);
    Groups after= app.group().all();
    assertEquals(after.size(),before.size());
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }


}
